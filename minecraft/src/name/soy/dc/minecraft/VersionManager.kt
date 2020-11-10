package name.soy.dc.minecraft

import name.soy.dc.minecraft.lib.*
import kotlin.concurrent.thread

class VersionManager {
	var system_mirror:Mirror = Mirror.NO_MIRROR
	
	var libs = arrayListOf<VersionLib>()
	
	var vanilla = VanillaVersionLib(this).apply(libs::add)
	
	var fabric = FabricVersionLib(this).apply(libs::add)
	
	var spigot = SpigotVersionLib(this).apply(libs::add)
	
	var paper = PaperVersionLib(this).apply(libs::add)

	var checkThread = thread(name = "mc_update_check") {
		while(true){
			Thread.sleep(30*60*1000)

		}
	}
}