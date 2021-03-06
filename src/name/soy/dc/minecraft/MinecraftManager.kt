package name.soy.dc.minecraft

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import name.soy.dc.device.IDevice
import name.soy.dc.minecraft.handle.IMinecraftServerManager
import java.io.File
import kotlin.collections.HashMap

@Manager("minecraft")
class MinecraftManager(val center: DataCenter, override var path: File): IMinecraftServerManager() {
	var hubs: HashMap<IDevice, IMinecraftHub> = hashMapOf()

	var versionManager: VersionManager = VersionManager(this)
	
	var universe:ServerUniverse = ServerUniverse(this)
	
}