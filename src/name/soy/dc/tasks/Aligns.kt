package name.soy.dc.tasks

import java.io.File
import java.util.*

class Aligns<T> private constructor(val isNeeded: Boolean, val suggestions: Set<T>?, val defaultValue: T?, val typename: String, val isList: Boolean) {
	override fun toString(): String {
		return "Aligns(needed=$isNeeded, suggestions=$suggestions, defaultValue=$defaultValue, typename=$typename, isList=$isList)"
	}

	companion object {
		fun createInt(suggestions: Set<Int>?, defaultValue: Int, needed: Boolean, list: Boolean = false): Aligns<Int> {
			return Aligns(needed, suggestions, defaultValue, "int", list)
		}

		fun createString(suggestions: Set<String>?, defaultValue: String?, needed: Boolean, list: Boolean = false): Aligns<String> {
			return Aligns(needed, suggestions, defaultValue, "string", list)
		}

		fun createDouble(suggestions: Set<Double>?, defaultValue: Double, needed: Boolean, list: Boolean = false): Aligns<Double> {
			return Aligns(needed, suggestions, defaultValue, "double", list)
		}

		fun <T : Enum<T>?> createEnum(enumClass: Class<T>, suggestionNumber: Int, needed: Boolean, list: Boolean = false): Aligns<T> {
			val ts = enumClass.enumConstants
			return Aligns(needed, HashSet(mutableListOf(*ts)), ts[suggestionNumber], "enum", list)
		}

		fun createFile(needed: Boolean, list: Boolean = false): Aligns<File?> {
			return Aligns(needed, null, null, "file", list)
		}
	}
}