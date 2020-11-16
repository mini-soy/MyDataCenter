package name.soy.dc.minecraft

import name.soy.dc.device.IDevice
import name.soy.dc.minecraft.runtime.MinecraftServerInstance
import java.util.*

/**
 * 一个子服务器类(client)
 *
 */
interface IMinecraftHub {
	fun device(): IDevice

	fun instances(): HashMap<String, MinecraftServerInstance>
}