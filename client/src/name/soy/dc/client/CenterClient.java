package name.soy.dc.client;

import name.soy.dc.client.minecraft.MinecraftServerManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.Properties;

public class CenterClient {
	public static CenterClient center;
	String computerName;
	File config;
	MinecraftServerManager mcsm;
	Properties properties;

	CenterClient() throws IOException {
		computerName = Inet4Address.getLocalHost().getHostName();
		System.out.println(computerName);

		config = new File("config.properties");
		properties = new Properties();

		if (!config.exists()) {
			properties.setProperty("ip", "mapland.cn");
			properties.setProperty("port", "21212");
			properties.setProperty("private-code", "set bytecode in there");
			properties.setProperty("minecraft_content", "false");
			properties.setProperty("device_content", "true");
			properties.store(new FileWriter(config), "default properties");
		} else properties.load(new FileReader(config));

		if (Boolean.parseBoolean(properties.getProperty("minecraft_content"))) {
			mcsm = new MinecraftServerManager(this);
		}
	}

	public static void main(String[] args) {
		if (center != null) {
			throw new RuntimeException("main函数已经运行,不允许二次调用");
		}
		try {
			center = new CenterClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
