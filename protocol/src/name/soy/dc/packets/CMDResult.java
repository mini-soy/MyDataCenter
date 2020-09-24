package name.soy.dc.packets;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMDResult implements Packet {
    int id;
    String readData;
    int exitcode = 0;
    int runTime = 0;

    public int requireLevel() {
        return LEVEL_SYSTEM;
    }

    public int direction() {
        return CLIENT_TO_SERVER;
    }

}
