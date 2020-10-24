package name.soy.dc.web.servlet.ws

import name.soy.dc.device.IDevice
import javax.websocket.Session

class MonitorSession(val session: Session) {
	var authed = false
	lateinit var device: IDevice
	fun disconnect() {}

	init {
		if (!Monitor.dsessions.containsKey(device)) {
			Monitor.dsessions[device] = arrayListOf()
		}
		Monitor.dsessions[device]?.add(this)
	}
}