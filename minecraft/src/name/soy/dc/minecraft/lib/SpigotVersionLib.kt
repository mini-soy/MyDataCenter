package name.soy.dc.minecraft.lib

import name.soy.dc.minecraft.VersionManager
import org.jsoup.Jsoup

class SpigotVersionLib(versionManager: VersionManager) : VersionLib {
	companion object{
	
	}
	/**
	 * 最新的构建版本
	 */
	@Volatile
	var newestVersion: String = ""
	
	/**
	 * latest.json
	 */
	@Volatile
	var latestVersion: String = ""
	
	/**
	 * 所有可用版本
	 */
	@Volatile
	var versions = arrayListOf<String>()
	
	override fun checkForNew() {
	
	}
	
	override fun onNewVersion(version: String, new: Boolean) {
		TODO("Not yet implemented")
	}
}