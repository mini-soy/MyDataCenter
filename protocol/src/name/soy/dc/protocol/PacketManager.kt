package name.soy.dc.protocol

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream
import io.netty.buffer.ByteBuf
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

class PacketManager {
	companion object{
		fun encodeObject(value: Serializable, buf: ByteBuf) =
			with(buf) {
				val out = ByteArrayOutputStream()
				val o_out = ObjectOutputStream(out)
				o_out.writeObject(value)
				o_out.flush()
				val buffer = out.toByteArray()
				writeInt(buffer.size)
				writeBytes(buffer)
			}

		fun readObject(buf: ByteBuf): Serializable {
			val readcount = buf.readInt()
			val buffer = ByteArray(readcount)
			buf.readBytes(buffer)
			val `in` = ByteInputStream()
			`in`.setBuf(buffer)
			val o_in = ObjectInputStream(`in`)
			return o_in.readObject() as Serializable
		}
		fun receivePacket(handler: name.soy.dc.protocol.packets.IClientHandler, buf: ByteBuf){
			var obj = readObject(buf)
			handler.javaClass.getMethod("onPacket",obj.javaClass).invoke(handler,obj)
		}
	}
}