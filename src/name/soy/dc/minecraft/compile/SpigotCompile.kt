package name.soy.dc.minecraft.compile

import name.soy.dc.tasks.Aligns
import name.soy.dc.tasks.exe.Executable
import name.soy.dc.tasks.exe.RemoteExecutable

class SpigotCompile: RemoteExecutable() {
    override fun remoteParameters(): () -> HashMap<String, Aligns<*>> = {
        HashMap<String,Aligns<*>>().apply {

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