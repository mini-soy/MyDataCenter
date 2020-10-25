package name.soy.dc.web.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/verify")
class Verify : HttpServlet() {
	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
	}

	companion object {
		/**
		 *
		 * @param req
		 * switch(@return)<br/>
		 * 0->sucess<br/>
		 * 1->failed<br/>
		 * -1->server failed<br/>
		 */
		fun validcode(req: HttpServletRequest): Int {
			return 0
		}

		fun validcode(code: String): Boolean {
			return true
		}
	}
}