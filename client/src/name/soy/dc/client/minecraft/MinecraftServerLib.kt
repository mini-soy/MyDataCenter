package name.soy.dc.client.minecraft

import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

//负责管理MC服务器产品的库
class MinecraftServerLib(manager: MinecraftServerManager) {
	
	val libpath = File(manager.path, "library").apply {
		if (!isDirectory)
			mkdir()
	}
	
	val fabricpath = File(libpath, "fabric").apply {
		if (!isDirectory)
			mkdir()
	}
	
	val spigotpath = File(libpath, "spigot").apply {
		if (!isDirectory)
			mkdir()
	}
	
	val libproperties = Properties()
	
	init {
		File(libpath, "config.properties").apply {
			if (!exists()) {
				libproperties.setProperty("mc_version_url",
						"https://launchermeta.mojang.com/mc/game/version_manifest.json")
				
				libproperties.setProperty("fabric_installer_maven_url",
						"https://maven.fabricmc.net/net/fabricmc/fabric-installer/")
				
				libproperties.setProperty("fabric_installer_cmd",
						"java -jar \${fabric-jar} server -dir \${dir} -mcversion \${version}")
				
				libproperties.setProperty("spigot_buildtools_jenkins_url",
						"https://hub.spigotmc.org/jenkins/job/BuildTools/")
				
				libproperties.setProperty("spigot_buildtools_cmd",
						"java -jar \${buildtools-jar} --version \${version}")
				
				libproperties.setProperty("spigot_versions_html_url",
						"https://hub.spigotmc.org/versions/")
				
				libproperties.setProperty("bungeecord_jenkins_url",
						"https://ci.md-5.net/job/BungeeCord/")
				
				libproperties.setProperty("velocity_jenkins_url",
						"https://ci.velocitypowered.com/job/velocity/")
				
				libproperties.setProperty("paper_download_url",
						"https://papermc.io/api/v1/paper")
				
				libproperties.setProperty("waterfall_download_url",
						"https://papermc.io/api/v1/waterfall")
				
				
				
				this.createNewFile()
				libproperties.store(FileWriter(this), "")
			} else {
				libproperties.load(FileReader(this))
			}
		}
	}
	
	fun downloadVanillaServer(version: String = "latest", snapshot: Boolean = false) {
	
	}
	
	fun createFabricServer(fabric_loader_version: String = "latest", minecraft_version: String = "latest", path: File) {
	
	}
}