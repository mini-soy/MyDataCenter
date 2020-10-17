package name.soy.dc.client

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class ClientBase : ChannelInitializer<SocketChannel?>() {
	@Throws(Exception::class)
	override fun initChannel(ch: SocketChannel?) {
	}
}