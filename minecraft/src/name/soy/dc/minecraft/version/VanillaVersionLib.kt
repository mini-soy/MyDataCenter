package name.soy.dc.minecraft.version

import com.google.gson.JsonParser
import name.soy.dc.minecraft.Mirror
import name.soy.dc.minecraft.VersionManager
import name.soy.dc.task.Executables
import name.soy.dc.task.exe.FileDownload
import org.jsoup.Jsoup
import java.net.URL
import java.text.DateFormat
import java.util.*
import kotlin.collections.HashMap

class VanillaVersionLib(val manager: VersionManager) : VersionLib {
	companion object {
		const val url = "https://launchermeta.mojang.com/mc/game/version_manifest.json"
	}
	
	data class VanillaVersion(val version: String, val snapshot: Boolean, val release: Date, val version_json: URL)
	
	@Volatile
	lateinit var latestSnapshot:String
	
	@Volatile
	lateinit var latestRelease:String
	
	@Volatile
	var versions:HashMap<String, VanillaVersion> = hashMapOf()
	
	var inited = false
	
	operator fun get(version: String) = versions[version]
	
	override fun checkForNew() {
		var versionsJson = JsonParser.parseReader(
				Jsoup.connect(url)
						.ignoreContentType(true)
						.execute().bodyStream().reader()
		).asJsonObject
		
		versionsJson["latest"].asJsonObject.also {
			latestSnapshot = it["snapshot"].asString
			latestRelease = it["release"].asString
		}
		
		versionsJson["versions"].asJsonArray.forEach { version_json ->
			version_json.asJsonObject.also { version_obj ->
				val version = version_obj["id"].asString
				val vanilla = VanillaVersion(
						version,
						version_obj["type"].asString == "snapshot",
						DateFormat.getDateInstance().parse(version_obj["releaseTime"].asString),
						URL(version_obj["url"].asString))
				if (!versions.containsKey(version)) {
					versions[version] = vanilla
					if (!inited)
						onNewVersion(version)
				} else {
					if (vanilla != versions[version]) {
						onNewVersion(version, false)
					}
				}
			}
		}
		inited = true
	}
	
	override fun onNewVersion(version: String, new: Boolean) {
	
	}
	
	/**
	 * 延时请求
	 */
	fun downloadServer(version: VanillaVersion, mirror: Mirror = manager.system_mirror): Int {
		/**
		 * 标识
		 * 0:镜像
		 * -1:不适用镜像下载
		 * 1:下载失败
		 */
		var flag = 0;
		
		var versionJson = JsonParser.parseReader(
				version.version_json.openConnection().getInputStream().reader()
		).asJsonObject
		//备用下载方法
		var fallbackDownload = Executables.FILE_DOWNLOAD().execute().apply {
			setData(FileDownload.URL, versionJson["downloads"].asJsonObject["url"].asString)
			setData(FileDownload.UID, "vanilla-server-$version")
		}
		//镜像下载
		var serverDownload = Executables.FILE_DOWNLOAD().execute().apply {
			setData(FileDownload.URL, mirror..versionJson["downloads"].asJsonObject["url"].asString)
			setData(FileDownload.UID, "vanilla-server-$version")
		}
		serverDownload.postRun()
		serverDownload.waitFor()
		if (serverDownload.resCode != 0) {
			fallbackDownload.postRun()
			fallbackDownload.waitFor()
			if (fallbackDownload.resCode == 0) {
				flag = -1
			} else {
				flag = 1
			}
		} else {
			flag = 0
		}
		return flag
	}
}