package name.soy.dc.utils;

import com.google.gson.JsonObject;

import java.util.function.Supplier;

public enum WSMsg {
    INVALID_DEVICE(()->{
        JsonObject json = new JsonObject();
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
