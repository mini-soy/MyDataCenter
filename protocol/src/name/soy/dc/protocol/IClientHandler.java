package name.soy.dc.protocol;

import name.soy.dc.protocol.packets.Packet;
import name.soy.dc.protocol.packets.RegDevice;
import name.soy.dc.protocol.packets.minecraft.ServerInfo;
import name.soy.dc.protocol.packets.minecraft.ServerLog;
import name.soy.dc.protocol.packets.task.TaskCreate;
import name.soy.dc.protocol.packets.task.TaskResult;
import name.soy.dc.protocol.packets.task.TaskState;

public interface IClientHandler {
    void sendPacket(Packet packet);

    void onPacket(RegDevice packet);

    void onPacket(TaskCreate packet);

    void onPacket(TaskState packet);

    void onPacket(TaskResult packet);

    void onPacket(ServerInfo packet);

    void onPacket(ServerLog packet);


}
