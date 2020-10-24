package name.soy.dc.task

import java.io.File
import java.util.*

data class Aligns<T> private constructor(
		val needed: Boolean,//需要?
		var suggestions: Set<T>?,//建议列表
		var defaultValue: T?,//默认值
		val typename: String,//类型名称
		val isList: Boolean,//是否为多选
		val bounds:Boolean,//范围限制在suggestions下
) {
	companion object {
		fun createInt(suggestions: Set<Int>?, defaultValue: Int, needed: Boolean, list: Boolean = false,bounds: Boolean = false): Aligns<Int> {
			return Aligns(needed, suggestions, defaultValue, "int", list,bounds)
		}

		fun createString(suggestions: Set<String>?, defaultValue: String?, needed: Boolean, list: Boolean = false,bounds: Boolean = false): Aligns<String> {
			return Aligns(needed, suggestions, defaultValue, "string", list,bounds)
		}

		fun createDouble(suggestions: Set<Double>?, defaultValue: Double, needed: Boolean, list: Boolean = false,bounds: Boolean = false): Aligns<Double> {
			return Aligns(needed, suggestions, defaultValue, "double", list,bounds)
		}

		fun <T : Enum<T>> createEnum(enumClass: Class<T>, suggestionNumber: Int, needed: Boolean, list: Boolean = false): Aligns<T> {
			val ts: Array<T> = enumClass.enumConstants
			return Aligns(needed, HashSet(mutableListOf(*ts)), ts[suggestionNumber], "enum", list,true)
		}

		fun createFile(needed: Boolean, list: Boolean = false): Aligns<File?> {
			return Aligns(needed, null, null, "file", list,false)
		}
	}
}