package name.soy.dc.client;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
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

            if(!config.exists()){

            }

            Properties properties = new Properties();
            properties.load(new FileReader(config));


        } catch (IOException e) {}
    }
}
