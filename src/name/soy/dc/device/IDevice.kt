package name.soy.dc.device

import javafx.util.Callback
import name.soy.dc.device.aida.DeviceData
import name.soy.dc.packets.CMDResult

interface IDevice {
    fun sendcmd(cmd:String,callback: Callback<CMDResult, Unit>)

    fun getDeviceName():String

    fun getDeviceType():DeviceType

    fun getDeviceData():DeviceData

}