package name.soy.dc.utils

import com.google.gson.JsonObject
import java.util.function.Supplier

enum class WSMsg(o: Supplier<JsonObject>) {
	INVALID_DEVICE(Supplier<JsonObject> {
		val json = JsonObject()
		json.addProperty("level", "error")
		json.addProperty("data", "设备不存在！")
		json
	}),
	DEVICE_OK(Supplier<JsonObject> {
		val json = JsonObject()
		json.addProperty("level", "nice")
		json
	}),
	AIDA_DATA(Supplier<JsonObject> {
		val json = JsonObject()
		json.addProperty("level", "nice")
		json
	}),
	INVALID_SESSION(Supplier<JsonObject> {
		val json = JsonObject()
		json.addProperty("level", "error")
		json.addProperty("data", "验证失败")
		json
	});

	private val json: JsonObject
	fun get(): JsonObject {
		return json.deepCopy()
	}

	init {
		json = o.get()
		json.addProperty("type", name.toLowerCase())
	}
}