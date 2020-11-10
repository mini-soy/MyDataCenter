package name.soy.dc.utils

import com.google.gson.JsonObject
import java.util.function.Supplier

enum class WSMsg(o: Supplier<JsonObject>) {
	INVALID_DEVICE(Supplier<JsonObject> {
		JsonObject().apply {
			addProperty("level", "error")
			addProperty("data", "设备不存在！")
		}
		
	}),
	DEVICE_OK(Supplier<JsonObject> {
		JsonObject().apply {
			addProperty("level", "nice")
		}
		
	}),
	AIDA_DATA(Supplier<JsonObject> {
		JsonObject().apply {
			addProperty("level", "nice")
		}
	}),
	INVALID_SESSION(Supplier<JsonObject> {
		val json = JsonObject()
		json.addProperty("level", "error")
		json.addProperty("data", "验证失败")
		json
	});
	
	private val json: JsonObject = o.get().apply {
		addProperty("type", name.toLowerCase())
	}
	operator fun invoke(): JsonObject {
		return json.deepCopy()
	}
}