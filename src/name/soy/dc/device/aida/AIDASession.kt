package name.soy.dc.device.aida

import name.soy.dc.DataCenter
import name.soy.dc.log.LogEntry
import name.soy.dc.log.LogManager
import org.jsoup.helper.HttpConnection
import java.io.IOException
import java.io.InputStreamReader
import java.io.LineNumberReader
import java.util.logging.Level

/**
 * 基于AIDA64的LCD Remote Sensor来建立的连接检查系统
 */
class AIDASession(address:String, port:Int, var data: DeviceData):Thread("${data.device.getDeviceName()}->AIDASession") {
	var url = "http://$address:$port/sse"
    lateinit var stream:LineNumberReader
	init {
		init(0)
		this.start()
	}
	/**
	 * 未连接，重连所调用的方法
	 */
	fun init(count:Int) {
		if(count>=10) {
			DataCenter().log().log(LogEntry.DEVICE, Level.WARNING, "AIDA目标:\"$url\"已经尝试10次获取,均为失败")
			println("AIDA目标:\"${url}\"已经尝试10次获取,均为失败")
		}
		try {
            //AIDA连接目标
			val con = HttpConnection.connect(url)
			.timeout(100000)
			.header("Accept","text/event-stream")
			.header("Connection","keep-alive")
			.header("Cache-Control","no-cache")


			stream = LineNumberReader(InputStreamReader(con.execute().bodyStream(),"UTF-8"))
		} catch (e: Exception) {
			init(count+1)
		}
	}

	override fun run(){
		while(true){
			stream.use{
				var line = it.readLine()
				if(line.startsWith("data:")){
					//表示这已经是数据行了
					var value:HashMap<String,String> = HashMap()
					var sarr = line.split("{|}").toMutableList()
					sarr.removeAt(0)
					for(entry in sarr) {
						entry.split("|").apply {
							if (this.size >= 2&&(!(this[1].isEmpty() || this[1] == "N/A"))) {
								value[this[0]] = this[1]
							}
						}
					}
					data.pushAIDA(value)
				}
			}

		}
	}
}