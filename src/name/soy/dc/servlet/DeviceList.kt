package name.soy.dc.servlet

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import name.soy.dc.DataCenter
import name.soy.dc.device.IDevice
import java.io.IOException
import java.util.function.Consumer
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/devices")
class DeviceList : HttpServlet() {
	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
		resp.contentType = "application/json; charset=utf-8"
		val devices: List<IDevice> = DataCenter.Companion.center!!.device().getDevices()
		val arr = JsonArray()
		synchronized(devices) {
			devices.forEach(Consumer { e: IDevice ->
				val data = JsonObject()
				data.addProperty("deviceName", e.getDeviceName())
				data.addProperty("deviceType", e.getDeviceType().name.toLowerCase())
				arr.add(data)
			})
		}
		val test = JsonObject()
		test.addProperty("deviceName", "测试")
		test.addProperty("deviceType", "phone")
		arr.add(test)
		try {
			resp.writer.print(arr)
			resp.writer.close()
		} catch (e: IOException) {
			e.printStackTrace()
		}
	}

	companion object {
		private const val serialVersionUID = 6234863195538216602L
	}
}