package name.soy.dc.minecraft.runtime

import name.soy.dc.minecraft.MinecraftServer
import name.soy.dc.minecraft.handle.IMinecraftServerManager
import name.soy.dc.minecraft.runtime.type.RuntimeHandler
import name.soy.dc.minecraft.util.ProcessUtil
import name.soy.dc.protocol.data.minecraft.ServerType
import java.net.InetAddress
import java.nio.charset.Charset
import kotlin.concurrent.thread

class DedicatedServerInstance(
		private val manager: IMinecraftServerManager,
		var server: MinecraftServer,
		var process: Process
) : MinecraftServerInstance(server.name) {
	val handler:RuntimeHandler = RuntimeHandler(this);
	val daemon:Thread = thread(start = true,isDaemon = false,name= "mcserver-instance:${server.name}"){
		callExit(process.waitFor())
	}
	
	private fun callExit(exit_code: Int) =
			manager.serverListeners.forEach{ it.onServerStop(this,exit_code)}
	
	override fun isRemote() = false
	
	override fun sendCmd(cmd: String) {
		process.outputStream.apply {
			write(cmd.toByteArray(Charset.defaultCharset()))
			flush()
		}
	}
	
	override var type: ServerType = server.serverType;
	
	override fun getAddrs(): List<InetAddress> = handler.getAddrs();
	
	fun stop() = handler.stop()
	
	@Deprecated("not recommend,force stop may cause data losing",
			 ReplaceWith("stop()"))
	fun forceStop() = ProcessUtil.killProcess(process)
	
}