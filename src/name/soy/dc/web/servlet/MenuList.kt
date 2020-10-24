package name.soy.dc.web.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("menus")
class MenuList: HttpServlet() {
	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
		resp.contentType = "Content-Type: application/json; charset=utf-8"

		super.doGet(req, resp)
	}
}