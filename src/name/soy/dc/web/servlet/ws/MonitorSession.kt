package name.soy.dc.web.servlet.ws

import name.soy.dc.device.IDevice
import javax.websocket.Session

class MonitorSession(val session: Session) {
	var authed = false
	lateinit var device: IDevice
	fun disconnect() {}

	init {
		Monitor.dsessions.computeIfAbsent(device) { arrayListOf() }.add(this@MonitorSession);
	}
}