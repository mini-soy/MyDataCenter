package name.soy.dc.device;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import name.soy.dc.DataCenter;
import name.soy.dc.annotation.Manager;

@Manager
public class DeviceManager {
	public static final short SERVER_PORT = 21212;
	private LocalDevice local;

	public LocalDevice local(){
		return local;
	}
	/**
	 * 所有设备，包括本地设备
	 */
	@Getter
	Vector<IDevice> devices = new Vector<>();

	ServerSocket server;

	private DataCenter center;

	List<TempSocket> temps = new ArrayList<>();

	public DeviceManager(DataCenter center) {
		local = new LocalDevice();
		devices.add(local);

		this.center = center;
		new Thread(()->{
			try {
				//服务器端口,
				server = new ServerSocket(SERVER_PORT);

				for(;;) {
					synchronized (temps) {
						val tempSocket = new TempSocket(server.accept());
						temps.add(tempSocket);
						new Thread(tempSocket,"tempSocket-"+tempSocket.s.getInetAddress()).start();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		System.out.println("DeviceManager已经部署");
	}

    public IDevice getDevice(String device) {
		for(IDevice d:devices)
			if(d.getDeviceName().equals(device))return d;
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
					RemoteDevice d = new RemoteDevice(s);
					synchronized (temps) {
						temps.remove(this);
						synchronized (devices) {
							devices.add(d);
						}
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
