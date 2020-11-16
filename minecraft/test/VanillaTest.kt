import com.google.gson.JsonParser
import name.soy.dc.minecraft.version.VanillaVersionLib
import org.jsoup.Jsoup
import java.net.URL
import java.text.DateFormat

fun main() {
	var versionsJson = JsonParser.parseReader(
			Jsoup.connect(VanillaVersionLib.url)
					.ignoreContentType(true)
					.execute().bodyStream().reader()
	).asJsonObject
	
	versionsJson["latest"].asJsonObject.also {
		println("snapshot${it["snapshot"].asString}")
		println("release${it["release"].asString}")
	}
	
	versionsJson["versions"].asJsonArray.forEach { version_json ->
		version_json.asJsonObject.also { version_obj ->
			val version = version_obj["id"].asString
			val vanilla = VanillaVersionLib.VanillaVersion(
					version,
					version_obj["type"].asString == "snapshot",
					DateFormat.getDateInstance().parse(version_obj["releaseTime"].asString),
					URL(version_obj["url"].asString))
			print(vanilla)
		}
	}
}