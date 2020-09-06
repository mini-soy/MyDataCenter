package name.soy.dc;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class HardwareSystem {
	List<Device> devices = new ArrayList<Device>();
	ServerSocket server;
	public HardwareSystem() {
		try {
			server = new ServerSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
