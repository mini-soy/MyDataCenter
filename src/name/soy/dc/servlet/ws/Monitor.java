package name.soy.dc.servlet.ws;

import com.google.gson.JsonObject;
import lombok.Data;
import name.soy.dc.DataCenter;
import name.soy.dc.device.IDevice;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static name.soy.dc.utils.WSMsg.INVALID_DEVICE;

@ServerEndpoint("/device-monitor/{device}")
public class Monitor {

    public static HashMap<IDevice, List<MonitorSession>> dsessions = new HashMap<>();
    private static HashMap<Session,MonitorSession> sessions = new HashMap<>();
    @OnOpen
    public void onOpen(@PathParam("device")String device, Session session,EndpointConfig config){
//        System.out.println("新连接来了:"+session+"/"+device);
        IDevice d = DataCenter.center.deviceManager.getDevice(device);

        if(d==null){
            try {
                JsonObject obj = INVALID_DEVICE.get();

                session.getBasicRemote().sendText(obj.toString());
                session.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sessions.put(session, new MonitorSession(d, session));
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
