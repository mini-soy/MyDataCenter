package name.soy.dc.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/init")
public class Initialize extends HttpServlet {
	private static final long serialVersionUID = -8657678748192431648L;
	private static final Object lock = new Object();

	@Override
	public void init(ServletConfig config) throws ServletException {
		synchronized (lock) {
			try {
				Class.forName("name.soy.dc.DataCenter");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//标准json
		resp.setContentType("application/json; charset=utf-8");
		if(!Verify.isValid(req)){

			return;
		}
		super.doGet(req, resp);
	}
}
