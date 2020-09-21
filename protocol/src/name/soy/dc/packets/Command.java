package name.soy.dc.packets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Command implements Packet {
    int id;
    String command;
    @Override
    public int requireLevel() {
        return LEVEL_SYSTEM;
    }

    @Override
    public int direction() {
        return SERVER_TO_CLIENT;
    }
}
