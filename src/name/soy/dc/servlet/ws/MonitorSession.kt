package name.soy.dc.servlet.ws

import name.soy.dc.device.IDevice
import javax.websocket.Session

class MonitorSession(val session: Session) {
	var authed = false
	var device: IDevice? = null
	fun disconnect() {}

	init {
		if (!Monitor.dsessions.containsKey(device)) {
			Monitor.dsessions[device] = arrayListOf<MonitorSession>()
		}
		Monitor.dsessions[device]!!.add(this)
	}
}