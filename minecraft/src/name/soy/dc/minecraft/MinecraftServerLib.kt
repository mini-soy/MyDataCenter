package name.soy.dc.minecraft

import name.soy.dc.minecraft.handle.IMinecraftServerManager
import name.soy.dc.minecraft.lib.VanillaVersionLib
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

//负责管理MC服务器产品的库
class MinecraftServerLib(val version: VersionManager) {
	val manager = IMinecraftServerManager();
	val update_check = Thread({
	
	}, "minecraft update check")
	
	val libpath = File(manager.serverJarDir(), "library").apply {
		if (!isDirectory)
			mkdir()
	}
	
	val fabric_path = lib("fabric")
	
	val spigot_path = lib("spigot")
	
	val paper_path = lib("paper")
	
	val vanilla_path = lib("vanilla")
	
	val libproperties = Properties()
	private fun lib(path:String):File = File(libpath, "").apply {
		if (!isDirectory)
			mkdir()
	}
	init {
		update_check.start()
		File(libpath, "config.properties").apply {
			if (!exists()) {
				libproperties.setProperty("mc_version_url",
						"https://launchermeta.mojang.com/mc/game/version_manifest.json")
				
				libproperties.setProperty("fabric_installer_jenkins_url",
						"https://jenkins.modmuss50.me/job/FabricMC/job/fabric-installer/job/master")
				
				libproperties.setProperty("fabric_installer_cmd",
						"java -jar \${fabric-jar} server -dir \${dir} -mcversion \${version}")
				
				libproperties.setProperty("spigot_buildtools_jenkins_url",
						"https://hub.spigotmc.org/jenkins/job/BuildTools")
				
				libproperties.setProperty("spigot_buildtools_cmd",
						"java -jar \${buildtools-jar} --version \${version}")
				
				libproperties.setProperty("spigot_versions_html_url",
						"https://hub.spigotmc.org/versions")
				
				libproperties.setProperty("bungeecord_jenkins_url",
						"https://ci.md-5.net/job/BungeeCord")
				
				libproperties.setProperty("velocity_jenkins_url",
						"https://ci.velocitypowered.com/job/velocity")
				
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
	
	fun downloadVanillaServer(version: String = "latest", snapshot: Boolean = false, mirror: Mirror = this.version.system_mirror)  {
		var res = this.version.vanilla.run {
			when {
				//最新快照
				version == "latest" && snapshot ->  downloadServer(this[latestSnapshot]!!, mirror)
				//最新稳定版
				version == "latest" && !snapshot -> downloadServer(this[latestRelease]!!, mirror)
				//其他
				else -> downloadServer(this[version]!!, mirror)
			}
		}
	}
	fun getVanillaServerFile(version: VanillaVersionLib.VanillaVersion) = getVanillaServerFile(version.version)
	
	fun getVanillaServerFile(version: String):File = File(vanilla_path, "server-$version.jar")
	
	
	fun createFabricLoaderJar(fabric_loader_version: String = "latest", minecraft_version: String = "latest", path: File) {
	
	}
	
	fun downloadPaperServer(minecraft_version: String = "latest", build_version: String = "latest", path: File) {
	
	}
	
	fun createSpigotServer(version: String = "latest") {
	
	}
}