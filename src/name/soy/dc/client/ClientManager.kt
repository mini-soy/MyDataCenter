package name.soy.dc.client

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager

//客户端的服务中心
@Manager("client")
class ClientManager(val center: DataCenter) {
    var bootstrap: ServerBootstrap = ServerBootstrap()
    var group: EventLoopGroup = NioEventLoopGroup()
    var clients = HashMap<String,Client>()
    operator fun get(remoteAddr: String) = clients[remoteAddr]!!

    companion object {
        const val SERVER_PORT = 21212
    }

    init {
        try {
            val sync = bootstrap.group(group)
                    .channel(NioServerSocketChannel::class.java)
                    .childHandler(object : ChannelInitializer<SocketChannel>() {
                        override fun initChannel(ch: SocketChannel) {
                            ch.pipeline().addLast(ClientHello(ch,this@ClientManager))
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_BACKLOG, 128)
                    .bind(SERVER_PORT)
                    .sync();
            
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}