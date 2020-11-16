import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

fun main() {
	val dom = Jsoup.connect("https://hub.spigotmc.org/versions/").execute().parse()
	val body = dom.getElementsByTag("pre")[0]
	val text = body.text()
	val versions = arrayListOf<SpigotVersion>()
	var a_version_text = text.split("\n").filterIndexed { index, _ -> index != 0 };
	for (it in a_version_text) {
		var n = 0
		var version = ""
		var date = Date()
		var c = ""
		for (s in it.split("  ")) {
			if (s.isBlank()) continue
			when (n) {
				0 -> version = s
				
				1 -> {
					var str = s.trim();
					var cale = Calendar.getInstance()
					
					var dd_mm_yyyy = str.split(" ")[0].split("-")
					var hh_mm = str.split(" ")[1].split(":")
					try {
						var mm = dd_mm_yyyy[1].replace("Jan", "00")
								.replace("Feb", "01")
								.replace("Mar", "02")
								.replace("Apr", "03")
								.replace("May", "04")
								.replace("Jun", "05")
								.replace("Jul", "06")
								.replace("Aug", "07")
								.replace("Sep", "08")
								.replace("Oct", "09")
								.replace("Nov", "10")
								.replace("Dec", "11")
						cale.set(dd_mm_yyyy[2].toInt(), mm.toInt(), dd_mm_yyyy[0].toInt(), hh_mm[0].toInt(),
								hh_mm[1].toInt())
//					print(cale)
						c = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cale.time);
					} catch (e: Exception) {
						print(dd_mm_yyyy)
					} finally {
					
					}
				}
			}
			n++;
		}
		versions.add(SpigotVersion(version, c))
	}
	
	print(versions)
}
data class SpigotVersion(var version: String, val time: String)
