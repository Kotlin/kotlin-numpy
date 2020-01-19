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

enum class OSType {
    MACOS, LINUX, WINDOWS, UNKNOWN
}

data class PythonConf(val osType: OSType, val pythonHome: String, val pythonLibPath: String)

object LibraryLoader {
    private val nameNativeLib = System.mapLibraryName(baseNameNativeLib)
    private val tmpDir: File = Files.createTempDirectory("nativeKtNumPy").toFile().apply(File::deleteOnExit)
    val pythonConf by lazy {
        val osType = getOS()
        val pythonHome = System.getenv("PYTHONHOME") ?: getPythonEnv(pythonScriptName, "get_python_home")
        PythonConf(
            osType = osType,
            pythonHome = pythonHome,
            pythonLibPath = getPythonEnv(pythonScriptName, "get_pylib_path")
        )
    }

    fun loadLibraries() {
        val locationLib: String

        val exceptionMessage = StringBuilder()

        //extract py script from jar
        extractFileFromJar("/META-INF/pythonScript/$pythonScriptName.py", "$pythonScriptName.py")

        if (pythonConf.osType == OSType.WINDOWS) {
            //extract dll from jar
            extractFileFromJar("/META-INF/Native/$nameNativeLib", nameNativeLib)

            //load python lib
            if (!File(pythonConf.pythonLibPath).exists()) {
                throw FileNotFoundException(pythonConf.pythonLibPath)
            }
            System.load(pythonConf.pythonLibPath)

            locationLib = "${tmpDir.absolutePath}/$nameNativeLib"
        } else {
            var pipOut = execCommand("python", "-m", "pip", "show", "ktnumpy")

            if (pipOut.isEmpty() || "Package(s) not found" in pipOut) {
                execCommand("python", "-m", "pip", "install", "ktnumpy")
                pipOut = execCommand("python", "-m", "pip", "show", "ktnumpy")
            }

            locationLib = buildString {
                append(pipOut.substringAfter("Location: ").substringBefore("\n"))
                append("/ktnumpy/$nameNativeLib")
            }
        }

        //load ktnumpy from java.library.path
        try {
            System.loadLibrary(baseNameNativeLib)
            return
        } catch (e: UnsatisfiedLinkError) {
            exceptionMessage
                .append(e.message)
                .append("\n")
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
        if (errStr.isNotEmpty()) {
            throw Exception("\"$errStr\" when executing the command: ${commands.joinToString(" ")}")
        }

        return inStr
    }

    private fun extractFileFromJar(path: String, nameFile: String) {
        this::class.java.getResourceAsStream(path)
            .use { Files.copy(it, File(tmpDir, nameFile).apply(File::deleteOnExit).toPath()) }
    }

    private fun getPythonEnv(pythonFile: String, methodName: String): String =
        ProcessBuilder("python", "-c", "from $pythonFile import $methodName; print($methodName())")
            .directory(tmpDir)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
            .apply { waitFor() }
            .inputStream
            .bufferedReader()
            .readLine()
}

fun getOS(): OSType {
    val os = System.getProperty("os.name")
    return when {
        os.contains("Darwin") || os.contains("Mac OS X") -> OSType.MACOS
        os == "Linux" -> OSType.LINUX
        os.startsWith("Windows") -> OSType.WINDOWS
        else -> throw Exception("Operating system $os not yet supported")
    }
}