package name.soy.dc.web.servlet.ws

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import name.soy.dc.DataCenter
import name.soy.dc.device.IDevice
import name.soy.dc.web.servlet.Verify
import name.soy.dc.utils.WSMsg
import java.io.IOException
import java.util.*
import javax.websocket.*
import javax.websocket.server.ServerEndpoint

@ServerEndpoint("/device-monitor")
class Monitor {
	@OnOpen
	fun onOpen(session: Session, config: EndpointConfig) {
		sessions[session] = MonitorSession(session)
	}

	@OnClose
	fun onClose(session: Session, reason: CloseReason) {
	}

	@OnMessage
	fun onMessage(message: String, session: Session) {
		val obj = JsonParser.parseString(message) as JsonObject
		val msession: MonitorSession? = sessions[session]
		if (!msession!!.authed) {
			if (obj["type"].asString == "auth") {
				val vcode = obj["data"].asString
				if (Verify.validcode(vcode)) {
				} else {
					try {
						val sobj = WSMsg.INVALID_SESSION()
						session.basicRemote.sendText(sobj.toString())
						session.close()
						return
					} catch (e: IOException) {
						e.printStackTrace()
					}
				}
			} else {
				try {
					val sobj = WSMsg.INVALID_SESSION()
					session.basicRemote.sendText(sobj.toString())
					session.close()
					return
				} catch (e: IOException) {
					e.printStackTrace()
				}
			}
			return
		}
		if (msession.device == null) {
			if (obj["type"].asString == "set-device") {
				val dcode = obj["data"].asString
				val d: IDevice? = DataCenter().device()[dcode]
				if (d == null) {
					try {
						val sobj = WSMsg.INVALID_DEVICE()
						session.basicRemote.sendText(sobj.toString())
						return
					} catch (e: IOException) {
						e.printStackTrace()
					}
				}
			} else {
				try {
					val sobj = WSMsg.INVALID_DEVICE()
					session.basicRemote.sendText(sobj.toString())
					return
				} catch (e: IOException) {
					e.printStackTrace()
				}
			}
		} else {
		}
	}

	@OnError
	fun onError(session: Session, error: Throwable) {
		error.printStackTrace()
	}

	companion object {
		var dsessions = HashMap<IDevice, MutableList<MonitorSession>>()
		private val sessions = HashMap<Session, MonitorSession>()
	}
}