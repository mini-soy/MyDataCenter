package name.soy.dc.minecraft

import name.soy.dc.minecraft.handle.IMinecraftServerManager
import name.soy.dc.minecraft.version.*
import kotlin.concurrent.thread

class VersionManager(val manager: IMinecraftServerManager) {
	var system_mirror:Mirror = Mirror.NO_MIRROR
	
	var sub_libs:ArrayList<VersionLib> = arrayListOf()
	
	var vanilla:VanillaVersionLib = VanillaVersionLib(this).apply(sub_libs::add)
	
	var fabric:FabricVersionLib = FabricVersionLib(this).apply(sub_libs::add)
	
	var spigot:SpigotVersionLib = SpigotVersionLib(this).apply(sub_libs::add)
	
	var paper:PaperVersionLib = PaperVersionLib(this).apply(sub_libs::add)

	var lib = MinecraftServerLib(this)
	var checkThread = thread(name = "mc_update_check",start = true) {
		while(true){
			Thread.sleep(30*60*1000)//半小时检查一次
			
		}
	}
}