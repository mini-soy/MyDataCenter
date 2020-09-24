package name.soy.dc.device.aida;

import lombok.Getter;

/**
 * AIDA一个实例对象
 * @param <T>
 */
@Getter
public class AIDAEntry<T> {
	AIDAEntrySimple simple;
	T data;
	public AIDAEntry(AIDAEntrySimple simple, T data) {
		this.simple = simple;
		this.data = data;
	}
}
