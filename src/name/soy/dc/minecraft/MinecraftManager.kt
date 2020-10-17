package name.soy.dc.minecraft

import name.soy.dc.annotation.Manager
import name.soy.dc.device.IDevice
import java.util.*

@Manager("minecraft")
class MinecraftManager {
	var hubs: HashMap<IDevice, IMinecaftHub>? = null
	var versionManager: VersionManager? = null
}