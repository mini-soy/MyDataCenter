package name.soy.dc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//Socket(内部网络)
class Device(val socket:Socket) {
	var i = ObjectInputStream(BufferedInputStream(socket.getInputStream(),10240));
	var o = ObjectOutputStream(BufferedOutputStream(socket.getOutputStream(),10240));
	val devicename = i.readUTF()
		get() = field
	var AIDASession:AIDASession = AIDASession(socket.getInetAddress().getHostName(), i.readUnsignedShort())
	
	
	fun sendcmd(cmd:String) {
		
	}
	
}