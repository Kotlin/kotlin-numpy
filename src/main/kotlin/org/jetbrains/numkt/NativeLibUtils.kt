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

private const val namePythonScript = "utils"
private const val baseNameNativeLib = "ktnumpy"

enum class OSType {
    MACOS, LINUX, WINDOWS, UNKNOWN
}

data class PythonConf(val osType: OSType, val pythonHome: String, val pythonLibPath: String)

object LibraryLoader {
    private val nameNativeLib = System.mapLibraryName(baseNameNativeLib)
    private val tmpDir: File //= Files.createTempDirectory("nativeKtNumPy").toFile().apply { deleteOnExit() }
    val pythonConf by lazy {
        val osType = getOS()
        val pythonHome = System.getenv("PYTHONHOME") ?: getPythonEnv(namePythonScript, "get_python_home")
        PythonConf(
            osType = osType,
            pythonHome = pythonHome,
            pythonLibPath = if (osType == OSType.WINDOWS) buildString {
                append(pythonHome)
                append('\\')
                append(getPythonEnv(namePythonScript, "get_pylib"))
                append(".dll")
            } else getPythonEnv(
                namePythonScript,
                "get_pylib_path"
            )
        )
    }

    init {
        val dirScr = File("buildScr/python")
        if (dirScr.exists()) {
            tmpDir = dirScr
        } else {
            tmpDir = Files.createTempDirectory("nativeKtNumPy").toFile().apply { deleteOnExit() }

            //extract py script and native lib from jar
            mapOf(
                "/META-INF/pythonScript/$namePythonScript.py" to "$namePythonScript.py",
                "/META-INF/Native/$nameNativeLib" to nameNativeLib
            ).forEach {
                extractFileFromJar(it.key, it.value)
            }
        }
    }

    fun loadLibraries() {

        //load python lib
        if (!File(pythonConf.pythonLibPath).exists()) {
            throw FileNotFoundException(pythonConf.pythonLibPath)
        }
        System.load(pythonConf.pythonLibPath)

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

        try {
            // load native lib
            System.load("${tmpDir.absolutePath}/$nameNativeLib")
        } catch (e: UnsatisfiedLinkError) {
            exceptionMessage
                .append(e.message)
                .append("\n")
            throw UnsatisfiedLinkError(exceptionMessage.toString())
        }
    }

    private fun extractFileFromJar(path: String, nameFile: String) {
        this::class.java.getResourceAsStream(path)
            .use { Files.copy(it, File(tmpDir, nameFile).apply { deleteOnExit() }.toPath()) }
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