package name.soy.dc.client;

import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class CenterClient {
    static String computername;
    File config;

    public static void main(String[] args) {
        try {
            computername = Inet4Address.getLocalHost().getHostName();

            System.out.println(computername);

        } catch (UnknownHostException e) {}
    }
}
