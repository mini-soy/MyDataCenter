package name.soy.dc.servlet

import java.io.IOException
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Initialize : HttpServlet() {
	@Throws(ServletException::class)
	override fun init(config: ServletConfig) {
		synchronized(lock) {
			try {
				Class.forName("name.soy.dc.DataCenter")
			} catch (e: ClassNotFoundException) {
				e.printStackTrace()
			}
		}
		super.init(config)
	}

	@Throws(ServletException::class, IOException::class)
	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
		resp.writer.write("init:初始化的内容")
		super.doGet(req, resp)
	}

	companion object {
		private const val serialVersionUID = -8657678748192431648L
		private val lock = Any()
	}
}