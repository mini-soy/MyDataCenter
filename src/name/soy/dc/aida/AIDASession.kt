package name.soy.dc

import org.jsoup.helper.HttpConnection
import java.io.InputStream
import java.io.LineNumberReader
import java.io.InputStreamReader
import java.io.IOException


class AIDASession(address:String,port:Int):Thread() {
	var url = "http://${address}:${port}/sse"
	lateinit var stream:LineNumberReader;
	init{
		init();
		this.start()
	}
	fun init(){
		var con = HttpConnection.connect(url);
		con.header("Accept","text/event-stream")
		con.header("Connection","keep-alive")
		con.header("Cache-Control","no-cache")
		
		stream = LineNumberReader(InputStreamReader(con.execute().bodyStream()))
	}
	override fun run(){
		while(true){
			try{
				var line = stream.readLine()
				if(line.startsWith("data:")){
					
				}
			}catch(e: IOException){
				init()
			}
		}
	}
}