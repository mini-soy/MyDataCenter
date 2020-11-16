package name.soy.dc.minecraft.handle

import name.soy.dc.minecraft.MinecraftServer
import name.soy.dc.minecraft.runtime.DedicatedServerInstance

interface ServerListener {
	fun onServerStart(instance:DedicatedServerInstance){}
	
	fun onServerStop(instance: DedicatedServerInstance, exit_code: Int){}
	
	fun onServerCreate(server:MinecraftServer){}
	
	fun onServerRemove(server:MinecraftServer){}
	
	
}