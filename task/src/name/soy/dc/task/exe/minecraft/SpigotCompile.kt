package name.soy.dc.task.exe.minecraft

import name.soy.dc.task.Aligns
import name.soy.dc.task.exe.Executable
import name.soy.dc.task.exe.RemoteExecutable


class SpigotCompile: RemoteExecutable() {
    override fun remoteParameters(): () -> HashMap<String, Aligns<*>> = {
        HashMap<String, Aligns<*>>().apply {
            this["version"] = Aligns.createString()
        }
    }

    override fun localExecute(): SpigotCompileProgress = SpigotCompileProgress(this)

    class SpigotCompileProgress(exe: SpigotCompile) : Executable.ExecuteProgress(exe) {
        var ciaddr = "https://hub.spigotmc.org/jenkins/job/BuildTools/"
        override fun run():Int {
            TODO("完成远程下载，并且构建")
        }
    }

    override fun getName(): String = "spigot compile"
    override fun returnParameters(): () -> HashMap<String, Class<*>> {
        TODO("Not yet implemented")
    }
}