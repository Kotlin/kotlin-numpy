tasks.register<Exec>("wheelBuild") {
    commandLine("python", "setup.py", "bdist_wheel")
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

