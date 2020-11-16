package name.soy.dc.minecraft.runtime.type

import name.soy.dc.minecraft.runtime.DedicatedServerInstance
import name.soy.dc.minecraft.runtime.MinecraftServerInstance
import name.soy.dc.minecraft.util.ProcessUtil
import name.soy.dc.protocol.data.minecraft.ServerType
import name.soy.dc.protocol.data.minecraft.ServerType.*
import java.io.File
import java.net.InetAddress
import java.util.*

interface RuntimeHandler {
	val instance:DedicatedServerInstance
	
	fun stop()
	
	fun isWhitelisted()
	
	fun getProperties(): Properties
	
	fun getAddrs(): List<InetAddress> {
		val pid = ProcessUtil.getProcessId(instance.process);
		Runtime.getRuntime().exec("ipconfig -ano|findstr $pid");
		
	}
	
	fun file(file:String) = File(instance.server.path, file)
	
	companion object{
		operator fun invoke(instance: DedicatedServerInstance):RuntimeHandler = when(instance.type){
			//原生系分支
			VANILLA -> VanillaHandler(instance)
			BEDROCK-> TODO()
			
			FABRIC -> TODO()
			//Bukkit分支
			BUKKIT,SPIGOT,PAPER -> BukkitHandler(instance);
			
			FORGE -> TODO()
			SPONGE -> TODO()
			
			BUNGEECORD,WATERFALL -> TODO()
			
			VELOCITY -> TODO()
			GEYSER -> TODO()
			
			//你™是来干啥的
			UNKNOWN -> TODO()
		}
	}
}