package name.soy.dc.tasks;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Aligns<T> {
	final boolean needed;

	final Set<T> suggestions;

	final T defaultValue;

	final String typename;

	public static Aligns<Integer> createInt(Set<Integer> suggestions,int defaultValue,boolean needed){
		return new Aligns<>(needed, suggestions, defaultValue, "int");
	}
	public static Aligns<String> createString(Set<String> suggestions,String defaultValue,boolean needed){
		return new Aligns<>(needed, suggestions, defaultValue, "string");
	}
	public static Aligns<Double> createDouble(Set<Double> suggestions,double defaultValue,boolean needed){
		return new Aligns<>(needed, suggestions, defaultValue, "double");
	}
	public static <T extends Enum<T>> Aligns<T> createEnum(Class<T> enumclass,int suggestionNumber,boolean needed){
		T[] ts = enumclass.getEnumConstants();
		return new Aligns<>(needed, new HashSet<T>(Arrays.asList(ts)), ts[suggestionNumber], "enum");
	}
	public static Aligns<File> createFile(boolean needed){
		return new Aligns<>(needed, null, null, "file");
	}
}
