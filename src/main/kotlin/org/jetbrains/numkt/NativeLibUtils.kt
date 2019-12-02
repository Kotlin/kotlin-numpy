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

import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.nio.file.Files
import java.util.jar.JarFile

enum class OSType {
    MACOS, LINUX, WINDOWS, UNKNOWN
}

enum class ArchType {
    X86_64, ARM, UNKNOWN
}

fun loadLibrary(baseName: String) {

    val exception: Throwable
    try {
        System.loadLibrary(baseName)
        return
    } catch (e: Throwable) {
        exception = e
    }

    try {
        load(baseName)
    } catch (e: Throwable) {
        e.printStackTrace()
        exception.printStackTrace()
    }
}

fun load(baseName: String) {
    val libName = System.mapLibraryName(baseName)
    val osName = getOS()

    val libDest = File(
        when (osName) {
            OSType.MACOS -> "/usr/local/lib/$libName"
            OSType.LINUX -> "/usr/local/lib/$libName"
            OSType.WINDOWS -> "C:\\System32\\$libName"
            else -> "unknown"
        }
    )

    if (libDest.exists()) {
        System.load(libDest.absolutePath)
        return
    }

    val nativeLibraryPath = buildString {
        append("META-INF/Native/")
        when (osName) {
            OSType.MACOS -> append("macos")
            OSType.LINUX -> append("linux")
            OSType.WINDOWS -> append("win")
            else -> {
            }
        }
        append("/objs")
    }

    if (Interpreter::class.java.getResource("/$nativeLibraryPath") == null) {
        throw Exception("Error loading native library: $libName, missing objs from $nativeLibraryPath")
    }

    // create temp dir for uploading objs there
    // and delete it upon completion work vm
    val tempNativeFolder = Files.createTempDirectory("nativeKtnumpy").toFile()
    tempNativeFolder.deleteOnExit()

    // extract objs, script and install lib to PATH
    extractAndCompl(nativeLibraryPath, tempNativeFolder)

    // load library from PATH
    System.load(libDest.absolutePath)
}


fun extractAndCompl(nativeLibraryPath: String, tempNativeFolder: File) {

    // extract objs from jar
    // get jar
    val jarFile = File(Interpreter::class.java.protectionDomain.codeSource.location.path.replace("%20", " "))

    var pythonTmpScript: File? = null
    val namePythonScript = "buildKtNumPy.py"

    if (jarFile.isFile) {
        val jar = JarFile(jarFile)
        val entries = jar.entries()
        while (entries.hasMoreElements()) {
            val element = entries.nextElement()

            if (element.name.contains(namePythonScript)) {
                if (element.name.substringAfterLast("/") == "")
                    continue
                pythonTmpScript = File(tempNativeFolder, namePythonScript)
                pythonTmpScript.deleteOnExit()
                Interpreter::class.java.getResourceAsStream("/${element.name}")
                    .use { `in` -> Files.copy(`in`, pythonTmpScript.toPath()) }
                continue
            }

            // get obj or get python script
            if (element.name.startsWith("$nativeLibraryPath/")) {
                val nameObj = element.name.substringAfterLast("/")
                if (nameObj == "")
                    continue

                // copy obj to tmp folder
                val nativeTmpObj = File(tempNativeFolder, nameObj)
                nativeTmpObj.deleteOnExit()
                Interpreter::class.java.getResourceAsStream("/$nativeLibraryPath/$nameObj")
                    .use { `in` -> Files.copy(`in`, nativeTmpObj.toPath()) }
            }
        }
        jar.close()
    } else {
        throw FileNotFoundException("Not found jar: ${jarFile.absolutePath}")
    }

    // Run python script
    if (pythonTmpScript == null)
        throw FileNotFoundException("Python script not found: $namePythonScript")


    val builder = ProcessBuilder("python3", pythonTmpScript.absolutePath)
    builder.directory(tempNativeFolder)
    builder.redirectErrorStream()
    val p = builder.start().apply { waitFor() }
    val errorScript = BufferedReader(InputStreamReader(p.errorStream)).readLines()
    if (errorScript.isNotEmpty())
        throw Exception(errorScript.joinToString("\n"))
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