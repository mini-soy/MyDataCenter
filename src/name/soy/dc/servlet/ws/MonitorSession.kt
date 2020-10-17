package name.soy.dc.servlet.ws

import lombok.Data
import name.soy.dc.device.IDevice
import java.util.*
import javax.websocket.Session

@Data
class MonitorSession(val session: Session?) {
	var authed = false
	var device: IDevice? = null
	fun disconnect() {}

	init {
		if (!Monitor.Companion.dsessions.containsKey(device)) {
			Monitor.Companion.dsessions.put(device, ArrayList<MonitorSession>())
		}
		Monitor.Companion.dsessions.get(device)!!.add(this)
	}
}