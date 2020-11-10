package name.soy.dc.device

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import java.net.ServerSocket
import java.util.*

@Manager("device")
class DeviceManager(val center: DataCenter) {
	
	operator fun not() = center
	
	private val local = LocalDevice(this)
	
	fun local() = local
	
	/**
	 * 所有设备，包括本地设备
	 */
	var devices = Vector<IDevice>()
	
	operator fun get(device: String): IDevice? {
		for (d: IDevice in devices) if (d.getDeviceName() == device) return d
		return null
	}
	
	fun isLocal(device: String): Boolean {
		return local.getDeviceName() == device
	}
	
	init {
		devices.add(local)
		
		println("DeviceManager已经部署")
	}
}