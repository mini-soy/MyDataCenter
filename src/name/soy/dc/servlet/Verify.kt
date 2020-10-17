package name.soy.dc.servlet

import name.soy.dc.DataCenter
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/verify")
class Verify : HttpServlet() {
	@Throws(ServletException::class, IOException::class)
	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
	}

	companion object {
		fun isValid(req: HttpServletRequest?): Boolean {
			return DataCenter.center != null
		}

		/**
		 *
		 * @param req
		 * switch(@return)
		 * 0=>sucess
		 * 1=>failed
		 * -1=>server failed
		 */
		fun validcode(req: HttpServletRequest?): Int {
			return 0
		}

		fun validcode(code: String?): Boolean {
			return true
		}
	}
}