package name.soy.dc.minecraft.handle

import name.soy.dc.minecraft.MinecraftServerLib
import java.io.File


interface IMinecraftServerManager {
	/**
	 *
	 */
	var path: File
	
	var lib: MinecraftServerLib
	
	fun serverJarDir(): File = File(path,"jars").apply { if (!isDirectory) mkdir() }
	
	fun spigotBuildPath(): File = File(path, "spigot-build").apply { if (!isDirectory) mkdir() }
	
	companion object {
		private lateinit var handle: IMinecraftServerManager
		operator fun invoke() = handle
		fun handle(handle: IMinecraftServerManager) {
			this.handle = handle
		}
	}
}