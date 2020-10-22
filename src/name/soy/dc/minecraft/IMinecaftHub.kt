package name.soy.dc.minecraft

import name.soy.dc.device.IDevice
import java.util.*

/**
 * 一个子服务器类(client)
 *
 */
interface IMinecaftHub {
	fun device(): IDevice

	fun instances(): HashMap<String, MinecraftServerInstance>
}