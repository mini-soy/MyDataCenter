package name.soy.dc.web.servlet

import javax.servlet.ServletConfig
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class Initialize : HttpServlet() {
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

	override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
		resp.writer.write("init:初始化的内容")
		super.doGet(req, resp)
	}

	companion object {
		private val lock = Any()
	}
}