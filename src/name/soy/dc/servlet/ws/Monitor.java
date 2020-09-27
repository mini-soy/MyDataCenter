package name.soy.dc.servlet.ws;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import name.soy.dc.DataCenter;
import name.soy.dc.device.IDevice;
import name.soy.dc.servlet.Verify;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static name.soy.dc.utils.WSMsg.*;

@ServerEndpoint("/device-monitor")
public class Monitor {

    public static HashMap<IDevice, List<MonitorSession>> dsessions = new HashMap<>();
    private static HashMap<Session, MonitorSession> sessions = new HashMap<>();
    @OnOpen
    public void onOpen( Session session,EndpointConfig config){
        sessions.put(session, new MonitorSession(session));
    }



    @OnClose
    public void onClose(Session session,CloseReason reason) throws IOException {

    }

    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
        JsonObject obj = (JsonObject) new JsonParser().parse(message);
        MonitorSession msession = sessions.get(session);
        if(!msession.authed){
            if(obj.get("type").getAsString().equals("auth")){
                String vcode = obj.get("data").getAsString();
                if(Verify.validcode(vcode)){

                }else{
                    try {
                        JsonObject sobj = INVALID_SESSION.get();
                        session.getBasicRemote().sendText(sobj.toString());
                        session.close();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    JsonObject sobj = INVALID_SESSION.get();
                    session.getBasicRemote().sendText(sobj.toString());
                    session.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return;
        }
        if(msession.device==null) {
            if(obj.get("type").getAsString().equals("set-device")){
                String dcode = obj.get("data").getAsString();
                IDevice d = DataCenter.center.deviceManager.getDevice(dcode);
                if(d==null){
                    try {
                        JsonObject sobj = INVALID_DEVICE.get();
                        session.getBasicRemote().sendText(sobj.toString());
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    JsonObject sobj = INVALID_DEVICE.get();
                    session.getBasicRemote().sendText(sobj.toString());
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
