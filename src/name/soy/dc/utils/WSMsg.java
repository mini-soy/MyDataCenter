package name.soy.dc.utils;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum WSMsg {
    INVALID_DEVICE(()->{
        JsonObject json = new JsonObject();
        json.addProperty("level","error");
        json.addProperty("data","设备不存在！");
        return json;
    }),
    DEVICE_OK(()->{
        JsonObject json = new JsonObject();
        json.addProperty("level","nice");
        return json;
    }),
    AIDA_DATA(()->{
        JsonObject json = new JsonObject();
        json.addProperty("level","nice");
        return json;
    }),
    INVALID_SESSION(()->{
        JsonObject json = new JsonObject();
        json.addProperty("level","error");
        json.addProperty("data","验证失败");
        return json;
    });
    private JsonObject json;




    WSMsg(@NotNull Supplier<JsonObject> o) {
        json = o.get();
        json.addProperty("type",name().toLowerCase());
    }

    public JsonObject get() {
        return json.deepCopy();
    }
}
