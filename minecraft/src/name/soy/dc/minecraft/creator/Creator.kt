package name.soy.dc.minecraft.creator

import name.soy.dc.minecraft.handle.IMinecraftServerManager
import name.soy.dc.protocol.data.minecraft.ServerType
import name.soy.dc.protocol.data.minecraft.ServerType.*
import java.io.File
import kotlin.jvm.Throws

class Creator(private val manager: IMinecraftServerManager) {
	
	private inline fun serverPath(name: String) = File(manager.path, name).apply { mkdir() }
	@Throws(KotlinNullPointerException::class)
	fun universeCreate(name: String, type: ServerType, data: HashMap<String, String>) {
		val path = serverPath(name)
		when (type) {
			VANILLA -> createVanilla(path, data["version"]!!)
			
			BUKKIT -> createBukkit(path, data["version"]!!)
			
			SPIGOT -> createSpigot(path, data["version"]!!)
			
			BUNGEECORD -> createBungeeCord(path)
			
			FABRIC -> createFabric(path, data["version"]!!, data["loader_version"]!!)
			
			PAPER -> createPaper(path, data["version"]!!, Integer.parseInt(data["num"]!!))
			
			FORGE -> createForge(path, data["version"]!!)
			
			WATERFALL -> createWaterFall(path)
			
			SPONGE -> createSponge(path, data["version"]!!)
			
			VELOCITY -> createVelocity(path)
			
			GEYSER -> createGeyser(path)
			
			BEDROCK -> {
			
			}
			UNKNOWN -> throw IllegalArgumentException("create个锤子嘞！")
		}
	}
	
	private fun createGeyser(path: File) {
	
	}
	
	private fun createSponge(path: File, version: String) {
	
	}
	
	private fun createVelocity(path: File) {
	
	}
	
	private fun createWaterFall(path: File) {
	
	}
	
	private fun createForge(path: File, version: String) {
	
	}
	
	private fun createPaper(path: File, version: String, versionnum: Int) {
	
	}
	
	private fun createFabric(path: File, version: String, loader_verion: String) {
	
	}
	
	private fun createBungeeCord(path: File) {
	
	}
	
	@Deprecated("use spigot instead")
	private fun createBukkit(path: File, version: String) {
	
	}
	
	private fun createSpigot(path: File, version: String) {
	
	}
	
	private fun createVanilla(path: File, version: String) {
		manager.version.
	}
	
	
}
