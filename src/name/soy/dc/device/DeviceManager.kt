package name.soy.dc.device

import lombok.Getter
import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import java.net.ServerSocket
import java.util.*

@Manager("device")
class DeviceManager(center: DataCenter) {
	private val local: LocalDevice = LocalDevice(this)
	fun local(): LocalDevice {
		return local
	}

	/**
	 * 所有设备，包括本地设备
	 */
	@Getter
	var devices = Vector<IDevice>()
	internal val center: DataCenter
	fun getDevice(device: String): IDevice? {
		for (d in devices) if (d.getDeviceName() == device) return d
		return null
	}

	fun isLocal(device: String): Boolean {
		return local.getDeviceName() == device
	}

	init {
		devices.add(local)
		this.center = center
		println("DeviceManager已经部署")
	}
}