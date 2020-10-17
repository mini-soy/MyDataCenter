package name.soy.dc.client;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class CenterClient {
    static String computername;
    static File config;



    public static void main(String[] args) {
        try {
            computername = Inet4Address.getLocalHost().getHostName();
            System.out.println(computername);

            config = new File("config.properties");

            Properties properties = new Properties();
            if(!config.exists()){
                properties.setProperty("ip","mapland.cn");
                properties.setProperty("port","21212");
                properties.setProperty("private-code","set bytecode in there");
                properties.setProperty("minecraft_content","false");
                properties.setProperty("device_content","true");

                properties.store(new FileWriter(config),"default properties");
            }

            properties.load(new FileReader(config));

            Socket s = new Socket();
            s.connect(new InetSocketAddress((Integer) properties.get("ip")));
        } catch (IOException e) {}
    }
}
