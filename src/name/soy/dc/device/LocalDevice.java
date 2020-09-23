package name.soy.dc.device;

import javafx.util.Callback;
import kotlin.Unit;
import name.soy.dc.device.aida.AIDASession;
import name.soy.dc.device.aida.DeviceData;
import name.soy.dc.packets.CMDResult;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Properties;

public class LocalDevice implements IDevice {
    DeviceData data;
    AIDASession session;
    LocalDevice(){
        data = new DeviceData(this);
        session = new AIDASession("localhost",14789, data);
    }
    @Override
    public void sendcmd(@NotNull String cmd, @NotNull Callback<CMDResult, Unit> callback) {
        new Thread(()->{
            try {
                long startTime = System.currentTimeMillis();
                Process exec = Runtime.getRuntime().exec(cmd);
                Reader in = new InputStreamReader(exec.getInputStream(),"GBK");
                StringBuilder sb = new StringBuilder();
                for(;;) {
                    int c = in.read();
                    if(c==-1)break;
                    sb.append((char)c);
                }
                long endTime = System.currentTimeMillis();
                callback.call(new CMDResult(0,sb.toString(),exec.exitValue(), Math.toIntExact(startTime - endTime)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @NotNull
    @Override
    public String getDeviceName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "未知";
        }
    }

    @NotNull
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.CLOUD;
    }

    @NotNull
    @Override
    public DeviceData getDeviceData() {
        return data;
    }
}
