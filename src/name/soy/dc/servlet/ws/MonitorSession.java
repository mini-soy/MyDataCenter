package name.soy.dc.servlet.ws;

import lombok.Data;
import name.soy.dc.device.IDevice;

import javax.websocket.Session;
import java.util.ArrayList;

@Data
public class MonitorSession {
    public final Session session;
    boolean authed = false;

    public IDevice device;

    public MonitorSession(Session session) {
        this.session = session;
        if(!Monitor.dsessions.containsKey(device)){
            Monitor.dsessions.put(device, new ArrayList<>());
        }
        Monitor.dsessions.get(device).add(this);
    }

    public void disconnect(){

    }
}