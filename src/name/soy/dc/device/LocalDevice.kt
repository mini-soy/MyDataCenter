package name.soy.dc.device

import javafx.util.Callback
import name.soy.dc.client.Client
import name.soy.dc.device.aida.AIDASession
import name.soy.dc.device.aida.DeviceData
import name.soy.dc.task.exe.Executable
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.net.Inet4Address
import java.net.UnknownHostException
import java.util.function.Consumer

class LocalDevice(val manager: DeviceManager) : IDevice {
	var data: DeviceData
	var session: AIDASession

	override fun sendCmd(cmd: String, callback: Consumer<Executable.ExecuteProgress>) {
		TODO("Not yet implemented")
	}

	override fun getDeviceName(): String = try {
		Inet4Address.getLocalHost().hostName
	} catch (e: UnknownHostException) {
		"未知"
	}

	override fun getDeviceType(): DeviceType {
		return DeviceType.CLOUD
	}

	override fun getDeviceData(): DeviceData {
		return data
	}

	override fun unaryPlus(): Client = manager.center.client[getDeviceName()]


	init {
		data = DeviceData(this)
		session = AIDASession("localhost", 14789, data)
	}
}