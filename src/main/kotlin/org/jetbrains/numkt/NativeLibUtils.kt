/*
 * Copyright 2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.numkt

import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files

private const val pythonScriptName = "utils"
private const val baseNameNativeLib = "ktnumpy"

/** Type of current operating system. */
enum class OSType {
    MACOS, LINUX, WINDOWS, UNKNOWN
}

/** A configuration object for Python.
 *
 * @property osType is operating system.
 * @property pythonHome is the location of the standard Python libraries.
 * @property pythonLibPath is absolute path to *pythonlib*.
 */
data class PythonConf(val osType: OSType, val pythonHome: String, val pythonLibPath: String, val python: String)

/**
 * Object that provides loading of the native *ktnumpy* and python library.
 */
object LibraryLoader {
    private var version: String? = null
    private val nameNativeLib = System.mapLibraryName(baseNameNativeLib)
    private val tmpDir: File

    var pythonConf: PythonConf? = null
        private set

    init {
        val dirScr = File("buildScr/python")
        if (dirScr.exists()) {
            tmpDir = dirScr
        } else {
            tmpDir = Files.createTempDirectory("nativeKtNumPy").toFile().apply(File::deleteOnExit)
            version = this::class.java.`package`.implementationVersion

            //extract py script from jar
            extractFileFromJar("/META-INF/pythonScript/$pythonScriptName.py", "$pythonScriptName.py")
        }
    }

    /**
     * Sets paths to python executable and python std lib.
     *
     * NOTE: by default, directory specified in `PYTHONHOME` is used or directory returned from
     * `sysconfig.get_config_var('prefix')` command.
     *
     * @param pythonPath PYTHONHOME, directory where python is installed
     * @exception FileNotFoundException
     */
    fun setPythonConfig(pythonPath: String) {
        val pyHome = File(pythonPath)
        if (!pyHome.exists()) throw FileNotFoundException("No such $pyHome directory exists.")
        println(pyHome.absolutePath)
        val os = getOS()
        val pythonExePath = when (os) {
            OSType.WINDOWS -> Pair(File("$pyHome\\python.exe"), File("$pyHome\\python3.exe"))
            OSType.LINUX, OSType.MACOS -> Pair(File("$pyHome/bin/python"), File("$pyHome/bin/python3"))
            OSType.UNKNOWN -> throw Exception("Undefined operating system.")
        }
        val python = when {
            pythonExePath.first.exists() -> pythonExePath.first.absolutePath
            pythonExePath.second.exists() -> pythonExePath.second.absolutePath
            else -> throw FileNotFoundException("Python executable file not found.")
        }
        val pythonLibPath = getPythonEnv(pythonScriptName, "get_pylib_path", python)
        pythonConf = PythonConf(os, pyHome.absolutePath, pythonLibPath, python)
    }

    /**
     * Load *ktnumpy* and *pythonlib*.
     *
     * Pip is used to search for *ktnumpy*, if *ktnumpy* is not installed, pip installs the appropriate version.
     */
    fun loadLibraries() {
        // initialize Python
        if (pythonConf == null) {
            val osType = getOS()
            val pythonHome = System.getenv("PYTHONHOME") ?: getPythonEnv(pythonScriptName, "get_python_home")
            pythonConf = PythonConf(
                osType = osType,
                pythonHome = pythonHome,
                pythonLibPath = getPythonEnv(pythonScriptName, "get_pylib_path"),
                "python"
            )
        }

        val locationLib: String

        val exceptionMessage = StringBuilder()

        //load ktnumpy from java.library.path
        try {
            System.loadLibrary(baseNameNativeLib)
            return
        } catch (e: UnsatisfiedLinkError) {
            exceptionMessage
                .append(e.message)
                .append("\n")
        }

        // pip
        val pypiURL =
            if (version != null && version!!.contains("dev")) "https://test.pypi.org/simple/" else "https://pypi.org/simple/"

        var pipOut = execCommand(pythonConf!!.python, "-m", "pip", "show", "ktnumpy")

        if (pipOut.isEmpty() || "Package(s) not found" in pipOut) {
            execCommand(pythonConf!!.python, "-m", "pip", "install", "-i", pypiURL, "ktnumpy==$version")
            pipOut = execCommand(pythonConf!!.python, "-m", "pip", "show", "ktnumpy")
        }

        if (version != null && pipOut.substringAfter("Version: ").substringBefore("\n") != version) {
            execCommand(pythonConf!!.python, "-m", "pip", "install", "-i", pypiURL, "--upgrade", "ktnumpy==$version")
        }

        locationLib = if (pythonConf!!.osType == OSType.WINDOWS) {
            buildString {
                append(pythonConf!!.pythonHome)
                append("\\Lib\\site-packages\\ktnumpy\\$nameNativeLib")
            }
        } else {
            buildString {
                append(pipOut.substringAfter("Location: ").substringBefore("\n"))
                append("/ktnumpy/$nameNativeLib")
            }
        }

        try {
            // load native lib
            System.load(locationLib)
        } catch (e: UnsatisfiedLinkError) {
            exceptionMessage
                .append(e.message)
                .append("\n")
            throw UnsatisfiedLinkError(exceptionMessage.toString())
        }
    }

    private fun execCommand(vararg commands: String): String {
        val errStr: String
        val inStr: String
        ProcessBuilder(*commands)
            .start()
            .apply {
                waitFor()
                errStr = errorStream.bufferedReader().readText()
                inStr = inputStream.bufferedReader().readText()
            }
        return when {
            errStr.isEmpty() -> inStr
            "WARNING" in errStr -> errStr
            else -> throw Exception("\"${errStr.trim()}\" when executing the command: ${commands.joinToString(" ")}")
        }
    }

    private fun extractFileFromJar(path: String, nameFile: String) {
        this::class.java.getResourceAsStream(path)
            .use { Files.copy(it, File(tmpDir, nameFile).apply(File::deleteOnExit).toPath()) }
    }

    private fun getPythonEnv(pythonFile: String, methodName: String, command: String = "python"): String =
        ProcessBuilder(command, "-c", "from $pythonFile import $methodName; print($methodName())")
            .directory(tmpDir)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
            .apply { waitFor() }
            .inputStream
            .bufferedReader()
            .readLine()
}

private fun getOS(): OSType {
    val os = System.getProperty("os.name")
    return when {
        os.contains("Darwin") || os.contains("Mac OS X") -> OSType.MACOS
        os == "Linux" -> OSType.LINUX
        os.startsWith("Windows") -> OSType.WINDOWS
        else -> throw Exception("Operating system $os not yet supported")
    }
}