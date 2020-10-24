package name.soy.dc.client.minecraft

import java.io.File
import java.io.FileReader
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

	val configpath = File(libpath, "config.properties").apply {
		if (!exists()) {
			libproperties.setProperty("mc_version_url",
				"https://launchermeta.mojang.com/mc/game/version_manifest.json")

			libproperties.setProperty("fabric_installer_maven_url",
				"https://maven.fabricmc.net/net/fabricmc/fabric-installer/")

			libproperties.setProperty("fabric_installer_cmd",
					"java -jar \${fabric-jar} server -dir \${dir} -mcversion \${version}")

			libproperties.setProperty("spigot_buildtools_jenkins_url",
				"https://hub.spigotmc.org/jenkins/job/BuildTools/")

			libproperties.setProperty("spigot_versions_html_url",
					"https://hub.spigotmc.org/versions/")

			libproperties.setProperty("bungeecord_download_jenkins_url",
					"https://ci.md-5.net/job/BungeeCord/")



		} else {
			libproperties.load(FileReader(this))
		}
	}

}