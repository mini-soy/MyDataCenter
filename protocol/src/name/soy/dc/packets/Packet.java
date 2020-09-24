package name.soy.dc.packets;


import java.io.Serializable;

public interface Packet extends Serializable {
    int SERVER_TO_CLIENT = 1;
    int CLIENT_TO_SERVER = -1;

    int LEVEL_EVERYONE = 0;
    int LEVEL_NEARBY_FIRENDS = 1;
    int LEVEL_MY_ADMIN = 2;
    int LEVEL_SYSTEM = 3;
    int LEVEL_ONLY_ME = 4;


    int requireLevel();
    int direction();
}
