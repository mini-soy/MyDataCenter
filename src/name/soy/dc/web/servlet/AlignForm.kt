package name.soy.dc.web.servlet

import name.soy.dc.utils.Aligner
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/aligns")
class AlignForm : HttpServlet() {
	companion object{
		private val aligns:HashMap<String,Aligner> = HashMap()
		operator fun set(placeholder:String,data:Aligner) {
			aligns[placeholder] = data
		}
	}
	@Throws(ServletException::class, IOException::class)
	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
		resp.contentType = "application/json; charset=utf-8"
		
	}
}