package name.soy.dc.utils;

import com.google.gson.JsonObject;

import java.util.function.Supplier;

public enum WSMsg {
    INVALID_DEVICE(()->{
        JsonObject json = new JsonObject();
        json.addProperty("type","ERROR");
        json.addProperty("data","设备不存在！");
        return json;
    }),
    DEVICE_OK(()->{
        JsonObject json = new JsonObject();
        return json;
    }),
    ;
    private JsonObject json;

    WSMsg(Supplier<JsonObject> o) {
        json = o.get();
    }

    public JsonObject get() {
        return json.deepCopy();
    }
}
