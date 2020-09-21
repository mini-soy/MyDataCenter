package name.soy.dc.device.aida

import name.soy.dc.log.LogEntry
import name.soy.dc.log.LogManager
import org.jsoup.helper.HttpConnection
import java.io.LineNumberReader
import java.io.InputStreamReader
import java.io.IOException
import java.util.logging.Level
class AIDASession(address:String,port:Int,data: DeviceData):Thread() {
	var url = "http://${address}:${port}/sse"
	lateinit var data:DeviceData set
	lateinit var stream:LineNumberReader
	init {
		init(0)
		this.start()
	}

	fun init(i:Int) {
		if(i>=10)
			LogManager.get!!.log(LogEntry.AIDA, Level.WARNING,"AIDA目标:\"${url}\"已经尝试10次获取,均为失败")
		try {
			var con = HttpConnection.connect(url);

			con.header("Accept","text/event-stream")
			con.header("Connection","keep-alive")
			con.header("Cache-Control","no-cache")

			stream = LineNumberReader(InputStreamReader(con.execute().bodyStream()))
		} catch (e: Exception) {
			init(i+1);
		}
	}

	override fun run(){
		while(true){
			try{
				var line = stream.readLine()
				if(line.startsWith("data:")){//表示这已经是数据行了
					var value:HashMap<String,String> = HashMap()
					var sarr = line.split("\\{\\|\\}")
					for(entry in sarr) {
						var e_p = entry.split("\\|")
						if (e_p.size >= 2) {
							if (!(e_p[1].isEmpty() || e_p[1].equals("N/A"))) {
								value.put(e_p[0], e_p[1])
							}
						}
					}
					data.pushAIDA(value)
				}
			} catch (e: IOException) {
				init(0)
			}
		}
	}
}