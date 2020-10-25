package name.soy.dc.task.exe.minecraft

import name.soy.dc.task.Aligns
import name.soy.dc.task.Executables
import name.soy.dc.task.exe.Executable
import name.soy.dc.task.exe.JenkinsDownload
import name.soy.dc.task.exe.RemoteExecutable

class SpigotCompile: RemoteExecutable() {
    override fun remoteParameters(): () -> HashMap<String, Aligns<*>> = {
        HashMap<String, Aligns<*>>().apply {
            this["version"] = Aligns.createString(null,null,true)
        }
    }

    override fun localExecute(): SpigotCompileProgress = SpigotCompileProgress(this)

    class SpigotCompileProgress(exe: SpigotCompile) : Executable.ExecuteProgress(exe) {
	    var ciaddr = "https://hub.spigotmc.org/jenkins/job/BuildTools/"

	    override fun run(): Int {
		    
		    val execute = Executables.JENKINS_DOWNLOAD().execute()
		    execute.setData(JenkinsDownload.JENKINS_ROOT, "https://hub.spigotmc.org/jenkins")
		    execute.setData(JenkinsDownload.PROJECT, "BuildTools")

		    execute.postRun()

		    execute.monitor.join()

		    execute.addProgressStatChangeListener {
			    progressText = "正在下载BuildTools...(${(+it).text})"
			    progress = (+it).progress / 10
			    
		    }
			return -1;
	    }
    }

    override fun getName(): String = "spigot compile"

    override fun returnParameters(): () -> HashMap<String, Class<*>> = {
        HashMap<String, Class<*>>().apply {
	        this["file_size"] = Int::class.java
        }
    }
}