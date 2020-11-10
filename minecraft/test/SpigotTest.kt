import org.jsoup.Jsoup

fun main() {
	val dom = Jsoup.connect("https://hub.spigotmc.org/versions/").execute().parse()
	val body = dom.getElementsByTag("pre")[0]
	print(body.text())
	
}
