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
	
	final Set<T> sugeections;
	
	final T defaultValue;
	final String typename;
	
	public static Aligns<Integer> createIntAlign(Set<Integer> sugeections,int defaultValue,boolean needed){
		return new Aligns<Integer>(needed, sugeections, defaultValue, "int");
	}
	public static Aligns<String> createStringAlign(Set<String> sugeections,String defaultValue,boolean needed){
		return new Aligns<String>(needed, sugeections, defaultValue, "string");
	}
	public static Aligns<Integer> createIntAlign(Set<Integer> sugeections,Integer defaultValue,boolean needed){
		return new Aligns<Integer>(needed, sugeections, defaultValue, "int");
	}
	public static <T extends Enum<T>> Aligns<T> createEnumAlign(Class<T> enumclass,int sugeectionNumber,boolean needed){
		T[] ts = enumclass.getEnumConstants();
		return new Aligns<T>(needed, new HashSet<T>(Arrays.asList(ts)), ts[sugeectionNumber], "enum");
	}
	public static Aligns<File> createFileAlign(boolean needed){
		return new Aligns<File>(needed, null, null, "int");
	}
}
