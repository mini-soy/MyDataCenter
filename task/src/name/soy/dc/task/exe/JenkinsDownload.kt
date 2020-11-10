package name.soy.dc.task.exe

import com.google.gson.JsonParser
import name.soy.dc.task.Aligns
import name.soy.dc.task.Executables
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.rmi.server.UID

/**
 * 从ci服务器下载构建好的文件
 */
class JenkinsDownload : RemoteExecutable() {
	companion object {
		/**
		 * 地址:如http://ci.viaVersion.com/job/ViaVersion
		 */
		const val JENKINS_ROOT = "jenkins_root"
		const val PROJECT = "project"
		const val EXCLUDE = "exclude"
		const val NAME = "name"
		
		const val UIDS = "uids"
		const val BUILD_NUMBER = "build-num"
	}
	
	override fun remoteParameters(): () -> HashMap<String, Aligns<*>> = {
		HashMap<String, Aligns<*>>().apply {
			this[JENKINS_ROOT] = Aligns.createString(hashSetOf(), null, true, false)
			this[PROJECT] = Aligns.createString(hashSetOf(), null, true, false)
			this[EXCLUDE] = Aligns.createString(hashSetOf(), null, true, true)
		}
	}
	
	override fun localExecute(): Executable.ExecuteProgress = JenkinsProgress(this)
	
	override fun getName(): String = "ci download"
	
	override fun returnParameters(): () -> HashMap<String, Class<*>> = {
		HashMap<String, Class<*>>().apply {
			this[UIDS] = List::class.java
		}
	}
	
	class JenkinsProgress(override val exe: JenkinsDownload) : Executable.ExecuteProgress(exe) {
		override fun run(): Int {
			val jenkins_root = dataset[JENKINS_ROOT] as String
			val project = dataset[PROJECT] as String
			val json_url = "$jenkins_root/job/$project/api/json"
			val json_conn = URL(json_url).openConnection() as HttpURLConnection
			val json = JsonParser.parseReader(InputStreamReader(json_conn.inputStream, "UTF-8")).asJsonObject
			val lastbuild = json["lastSuccessfulBuild"].asJsonObject["Number"].asInt
			result[BUILD_NUMBER] = lastbuild
			
			val build_json_url = "$jenkins_root/job/$project/$lastbuild/api/json"
			val build_json_conn = URL(build_json_url).openConnection() as HttpURLConnection
			val build_json = JsonParser.parseReader(InputStreamReader(build_json_conn.inputStream, "UTF-8")).asJsonObject
			
			val excludes = dataset[EXCLUDE] as List<*>
			val downloads = arrayListOf<Executable.ExecuteProgress>()
			val uids = arrayListOf<String>()
			build_json["artifacts"].asJsonArray.forEach { artifact ->
				if (!excludes.contains(artifact.asJsonObject["displayPath"].asString)) {
					val filename = artifact.asJsonObject["fileName"].asString
					val relative = artifact.asJsonObject["relativePath"].asString
					val fd: Executable.ExecuteProgress = Executables.FILE_DOWNLOAD().execute()
					val uid = "${dataset[NAME] as String}-$lastbuild:$filename"
					fd.setData(FileDownload.URL, "$jenkins_root/job/$project/$lastbuild/artifact/$relative")
					fd.setData(FileDownload.UID, uid)
					fd.postRun()
					downloads.add(fd)
				}
			}
			downloads.forEach(Executable.ExecuteProgress::waitFor)
			downloads.forEach { uids += it.result[FileDownload.UID] as String }
			result[UIDS] = uids
			return 0
		}
	}
}