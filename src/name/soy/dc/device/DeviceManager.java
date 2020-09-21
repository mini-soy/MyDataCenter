package name.soy.dc.device;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import lombok.var;
import name.soy.dc.DataCenter;

public class DeviceManager {
	public static final short SERVER_PORT = 21212;
	/**
	 * 所有设备，包括本地设备
	 */
	@Getter
	List<IDevice> devices = new ArrayList<>();

	ServerSocket server;

	private DataCenter center;

	List<TempSocket> temps = new ArrayList<>();

	public DeviceManager(DataCenter center) {
		try {
			//服务器端口,
			server = new ServerSocket(SERVER_PORT);

			for(;;) {
				val tempSocket = new TempSocket(server.accept());
				temps.add(tempSocket);
				new Thread().start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public IDevice getDeive(String device) {
		for(IDevice d:devices)
			if(d.equals(device))return d;
		return null;
    }

    @RequiredArgsConstructor
	class TempSocket implements Runnable {
		final Socket s;

		@Override
		public void run() {
			byte ob[] = center.getPrivateData().deviceVerifyData();
			byte b[] = new byte[ob.length];

			try {
				s.getInputStream().read(b);
				if(Arrays.equals(ob, b)){
					Device d = new Device(s);
					synchronized (temps) {
						temps.remove(this);
						devices.add(d);
					}
				} else {
					s.getOutputStream().write("验证失败".getBytes());
					temps.remove(this);
					s.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
