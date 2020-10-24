package name.soy.dc.minecraft

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import name.soy.dc.device.IDevice
import kotlin.collections.HashMap

@Manager("minecraft")
class MinecraftManager(val center: DataCenter) {
	var hubs: HashMap<IDevice, IMinecraftHub> = hashMapOf()

	var versionManager: VersionManager = VersionManager(this)

}