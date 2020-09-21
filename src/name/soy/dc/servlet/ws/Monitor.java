package name.soy.dc.servlet.ws;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import name.soy.dc.DataCenter;
import name.soy.dc.device.IDevice;
import name.soy.dc.utils.MsgHeader;
import name.soy.dc.utils.WSMsg;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import static name.soy.dc.utils.WSMsg.*;
import static name.soy.dc.utils.MsgHeader.*;
@ServerEndpoint("/device-monitor/{device}")
public class Monitor {


    HashMap<Session,MonitorSession> sessions = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("device")String device, Session session,EndpointConfig config){
        IDevice d = DataCenter.center.deviceManager.getDeive(device);
        if(d!=null){
            try {
                sendText(session,ERROR,INVALID_DEVICE.get());

                session.close();
                return;
            } catch (IOException e) {}
        }
        sessions.put(session, new MonitorSession(d));
    }

    private void sendText(Session session, MsgHeader header, JsonObject obj) throws IOException {
        session.getBasicRemote().sendText(new StringBuilder().append(header).append(obj).toString());
    }

    @OnClose
    public void onClose(@PathParam("device")String device,Session session,CloseReason reason) throws IOException {

    }

    @OnMessage
    public void onMessage(@PathParam("device")String device,String message,Session session) throws IOException {
        
    }

    @OnError
    public void onError(@PathParam("device")String device,Session session, Throwable error) {
        error.printStackTrace();
    }

}
@Data
@RequiredArgsConstructor
class MonitorSession{
    boolean authed = false;

    final IDevice device;

}