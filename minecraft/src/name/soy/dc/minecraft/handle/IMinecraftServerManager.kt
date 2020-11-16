package name.soy.dc.minecraft.handle

import name.soy.dc.minecraft.MinecraftServerLib
import name.soy.dc.minecraft.VersionManager
import java.io.File


abstract class IMinecraftServerManager {
	/**
	 *
	 */
	abstract var path: File
	
	val version: VersionManager = VersionManager(this)
	val lib = MinecraftServerLib(version)
	val serverListeners: List<ServerListener> = arrayListOf()
	
	fun serverJarDir(): File = File(path, "jars").apply { if (!isDirectory) mkdir() }
	
	fun serverPath(): File = File(path, "servers").apply { if (!isDirectory) mkdir() }
	
	fun spigotBuildPath(): File = File(path, "spigot-build").apply { if (!isDirectory) mkdir() }
	
	companion object {
		private lateinit var handle: IMinecraftServerManager
		
		operator fun invoke() = handle
		
		fun handle(handle: IMinecraftServerManager) {
			this.handle = handle
		}
		
	}
}