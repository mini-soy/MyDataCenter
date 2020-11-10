package name.soy.dc.minecraft.lib

import com.google.gson.JsonParser
import com.sun.org.apache.xpath.internal.operations.Bool
import name.soy.dc.minecraft.Mirror
import name.soy.dc.minecraft.VersionManager
import name.soy.dc.task.Executables
import name.soy.dc.task.exe.FileDownload
import org.jsoup.Jsoup
import java.net.URL
import java.text.DateFormat
import java.util.*

class VanillaVersionLib(val manager: VersionManager) : VersionLib {
	companion object {
		const val url = "https://launchermeta.mojang.com/mc/game/version_manifest.json"
		
	}
	
	data class VanillaVersion(val version: String, val snapshot: Boolean, val release: Date, val version_json: URL)
	
	var latestSnapshot = ""
	
	var latestRelease = ""
	
	var versions = hashMapOf<String, VanillaVersion>();
	
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
		if (new) {
		
		} else {
		
		}
	}
	
	fun downloadServer(version: VanillaVersion, mirror: Mirror = manager.system_mirror): Boolean {
		var versionJson = JsonParser.parseReader(
				version.version_json.openConnection().getInputStream().reader()
		).asJsonObject
		var fallbackDownload = Executables.FILE_DOWNLOAD().execute().apply {
			setData(FileDownload.URL, versionJson["downloads"].asJsonObject["url"].asString)
			setData(FileDownload.UID, "vanilla-server-$version")
		}
		
		var serverDownload = Executables.FILE_DOWNLOAD().execute().apply {
			setData(FileDownload.URL, mirror..versionJson["downloads"].asJsonObject["url"].asString)
			setData(FileDownload.UID, "vanilla-server-$version")
			
			addResultListener {
				if (it.resCode != 0) {
					fallbackDownload.postRun();
				}
			}
		}
		serverDownload.postRun()
		
	}
}