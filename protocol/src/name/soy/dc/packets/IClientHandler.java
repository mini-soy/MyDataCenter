package name.soy.dc.packets;

import name.soy.dc.packets.minecraft.ServerData;
import name.soy.dc.packets.minecraft.ServerInfo;
import name.soy.dc.packets.minecraft.ServerLog;
import name.soy.dc.packets.task.TaskCreate;
import name.soy.dc.packets.task.TaskResult;
import name.soy.dc.packets.task.TaskStat;

public interface IClientHandler {
    void sendPacket(Packet packet);

    void onPacket(RegDevice packet);

    void onPacket(TaskCreate packet);

    void onPacket(TaskStat packet);

    void onPacket(TaskResult packet);

    void onPacket(ServerData packet);

    void onPacket(ServerInfo packet);

    void onPacket(ServerLog packet);


}
