package name.soy.dc.task.exe.minecraft

import name.soy.dc.task.Aligns
import name.soy.dc.task.Executables
import name.soy.dc.task.exe.Command
import name.soy.dc.task.exe.Executable
import name.soy.dc.task.exe.JenkinsDownload
import name.soy.dc.task.exe.RemoteExecutable
import name.soy.dc.task.handle.FileCacheHandler
import name.soy.dc.task.handle.MCServerCreateHandler
import java.io.File
import java.lang.IllegalStateException
import java.lang.RuntimeException

/**
 * 构建spigot,并且导入到资源文件夹
 */
class SpigotCompile : RemoteExecutable() {
	override fun remoteParameters(): () -> HashMap<String, Aligns<*>> = {
		HashMap<String, Aligns<*>>().apply {
			this["version"] = Aligns.createString(hashSetOf(), null, true)
		}
	}
	
	override fun localExecute(): SpigotCompileProgress = SpigotCompileProgress(this)
	
	class SpigotCompileProgress(exe: SpigotCompile) : Executable.ExecuteProgress(exe) {
		var ciAddr = "https://hub.spigotmc.org/jenkins/job/BuildTools"
		
		override fun run(): Int {
			//开始下载文件
			val execute = Executables.JENKINS_DOWNLOAD().execute()
			execute.setData(JenkinsDownload.JENKINS_ROOT, o = ciAddr)
			execute.setData(JenkinsDownload.PROJECT, o = "BuildTools")
			
			execute.postRun()
			
			execute.addProgressStatChangeListener {
				progressText = "正在下载BuildTools...(${(+it).text})"
				progress = (+it).progress / 10
			}
			execute.waitFor()
			if(execute.resCode!=1)
				throw RuntimeException("无法下载BuildTools，构建个锤子")
			//下载完毕，复制文件
			val build_folder = MCServerCreateHandler().spigotBuildFolder();
			val uids = execute.result[JenkinsDownload.UIDS] as List<String>
			if (uids.size != 1) {
				throw IllegalStateException("emmm")
			} else {
				var build_tool_jar = FileCacheHandler().getFile(uids[0])
				build_tool_jar.copyTo(File(build_folder,"BuildTools.jar"),true)
			}
			//复制完毕，准备执行
			val cmd = Executables.COMMAND().execute()
			cmd.setData("command", "java -jar BuildTools.jat --rev ${dataset["version"]}")
			cmd.setData("folder", build_folder.absolutePath)
			
			cmd.postRun()
			
			cmd.addProgressStatChangeListener {
				progressText = "构建spigot..."
				progress = 25
			}
			
			cmd.waitFor()
			if(cmd.resCode==0){
				return 0;
			}
			return -1
		}
	}
	
	override fun getName() = "spigot compile"
	
	override fun returnParameters(): () -> HashMap<String, Class<*>> = {
		HashMap<String, Class<*>>().apply {
			this["file_size"] = Long::class.java
		}
	}
}