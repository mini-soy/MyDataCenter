package name.soy.dc.utils;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
public
enum MsgHeader {
    LOGIN("0"),
    ERROR("e"),
    DEVICE_DATA("d"),
    DATA_UPDATE("D"),
    UPDATE_DEVICE("U"),
    UNKNOWN("");
    private static HashMap<String,MsgHeader> types = new HashMap<>();
    static {
        for(MsgHeader h:values()){
            types.put(h.header,h);
        }
    }
    final String header;


    @Override
    public String toString() {
        return header;
    }
    public static MsgHeader getHeader(String msg){
        MsgHeader header = types.get(msg.substring(0, 1));
        if(header==null)return UNKNOWN;
        return header;
    }
}