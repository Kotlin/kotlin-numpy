val stablePyPiURL by extra("https://upload.pypi.org/legacy/")
val testPyPiURL by extra("https://test.pypi.org/legacy/")
val stablePyPiUser by extra(rootProject.findProperty("stablePyPiUser") ?: "")
val stablePyPiPassword by extra(rootProject.findProperty("stablePyPiPassword") ?: "")
val devPyPiUser by extra(rootProject.findProperty("devPyPiUser") ?: "")
val devPyPiPassword by extra(rootProject.findProperty("devPyPiPassword") ?: "")

tasks.register<Exec>("wheelBuild") {
    commandLine("python", "setup.py", "bdist_wheel")
}

tasks.register<Exec>("sdistBuild") {
    commandLine("python", "setup.py", "sdist")
}

tasks.register("localInstallPyPackage") {
    doLast {
        exec {
            val whl = file("dist").listFiles { _, n -> n.startsWith("ktnumpy") }?.first()
            commandLine("pip", "install", "dist/${whl?.name ?: "*"}")
        }
    }
}

tasks.register<Exec>("pyClean") {
    commandLine("python", "setup.py", "clean")
}

data class PyPICredential(val repoURL: String, val username: String, val password: String)

fun uploadPyPi(username: String, password: String, repoURL: String) {
    if (file("dist").exists()) {
        exec {
            commandLine(
                "python", "-m",
                "twine", "upload",
                "-u", username,
                "-p", password,
                "--repository-url", repoURL,
                "dist/*"
            )
        }
    }
}

tasks.register("pypiUploadStable") {
    uploadPyPi(stablePyPiUser as String, stablePyPiPassword as String, stablePyPiURL)
}

tasks.register("pypiUploadTest") {
    uploadPyPi(devPyPiUser as String, devPyPiPassword as String, testPyPiURL)
}