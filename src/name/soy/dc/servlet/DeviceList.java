package name.soy.dc.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import name.soy.dc.DataCenter;
import name.soy.dc.device.IDevice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/devices")
public class DeviceList extends HttpServlet {
	private static final long serialVersionUID = 6234863195538216602L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("application/json; charset=utf-8");
		List<IDevice> devices = DataCenter.center.deviceManager.getDevices();
		JsonArray arr = new JsonArray();
		synchronized (devices) {
			devices.forEach((e) -> {
				JsonObject data = new JsonObject();
				data.addProperty("deviceName", e.getDeviceName());
				data.addProperty("deviceType", e.getDeviceType().name().toLowerCase());
				arr.add(data);
			});
		}
		try {
			resp.getWriter().print(arr);
			resp.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
