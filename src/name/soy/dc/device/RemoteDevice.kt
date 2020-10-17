package name.soy.dc.device

import name.soy.dc.client.Client
import name.soy.dc.device.aida.AIDASession
import name.soy.dc.device.aida.DeviceData
import name.soy.dc.packets.RegDevice
import name.soy.dc.tasks.exe.Command
import name.soy.dc.tasks.exe.Executable
import java.util.function.Consumer


//Socket(内部网络)
class RemoteDevice(val regpacket: RegDevice,val client: Client):Runnable,IDevice {

	private lateinit var deviceName:String
	private lateinit var aidaSession: AIDASession
	private lateinit var data: DeviceData

	private var deviceType: DeviceType = DeviceType.UNKNOWN

	override fun run(){
		deviceName = client.name
		deviceType = DeviceType.valueOf(regpacket.deviceType!!)
		data = DeviceData(this)
		aidaSession = AIDASession(deviceName, regpacket.aidaPort,data)
	}

	override fun sendCmd(cmd: String, callback: Consumer<Executable.ExecuteProgress>) {
		Command().execute()
	}


	override fun getDeviceName(): String = deviceName

	override fun getDeviceType(): DeviceType = deviceType

	override fun getDeviceData(): DeviceData = data

	override fun unaryPlus(): Client = client

}