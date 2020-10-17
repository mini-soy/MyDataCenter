package name.soy.dc.client

import name.soy.dc.device.IDevice

interface IClient {
	fun getDevice(): IDevice?
	fun canPostTask(): Boolean
	val name: String
	fun isLocal(): Boolean
}