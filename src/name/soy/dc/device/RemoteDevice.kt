package name.soy.dc.device;

import javafx.util.Callback
import name.soy.dc.device.aida.AIDASession
import name.soy.dc.device.aida.DeviceData
import name.soy.dc.packets.CMDResult
import name.soy.dc.packets.Command
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket

//Socket(内部网络)
class RemoteDevice(private val socket: Socket):Runnable,IDevice {
	private lateinit var i:ObjectInputStream
	private lateinit var o:ObjectOutputStream

	private lateinit var deviceName:String
	private lateinit var AIDASession: AIDASession

	private lateinit var data: DeviceData

	private var deviceType: DeviceType = DeviceType.UNKNOWN

	private var cmdcallbacks = ArrayList<Callback<CMDResult,Unit>>()


	override fun run(){

		i = ObjectInputStream(BufferedInputStream(socket.getInputStream(),10240))
		o = ObjectOutputStream(BufferedOutputStream(socket.getOutputStream(),10240))

		deviceName = i.readUTF()
		deviceType = DeviceType.valueOf(i.readUTF())
		data = DeviceData(this)
		AIDASession = AIDASession(socket.inetAddress.hostName, i.readUnsignedShort(),data)

		while(true){
			var obj = i.readObject()
			if(obj is CMDResult){
				cmdcallbacks[obj.id].call(null)
				cmdcallbacks.removeAt(obj.id)
			} else {

			}

		}
	}
	
	override fun sendcmd(cmd: String, callback: Callback<CMDResult,Unit>) {
		synchronized(cmdcallbacks) {
			var id = cmdcallbacks.size
			cmdcallbacks[id] = callback
			o.writeObject(Command(id,cmd))
		}
	}

	override fun getDeviceName(): String = deviceName
	override fun getDeviceType(): DeviceType = deviceType
	override fun getDeviceData(): DeviceData = data

}