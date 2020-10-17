package name.soy.dc.device

import name.soy.dc.client.Client
import name.soy.dc.device.aida.DeviceData
import name.soy.dc.tasks.exe.Executable
import java.util.function.Consumer

interface IDevice {
    fun sendCmd(cmd:String, callback: Consumer<Executable.ExecuteProgress>):Unit

    fun getDeviceName():String

    fun getDeviceType():DeviceType

    fun getDeviceData():DeviceData

    operator fun unaryPlus():Client

}