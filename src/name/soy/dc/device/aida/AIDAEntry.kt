package name.soy.dc.device.aida

import lombok.Getter

/**
 * AIDA一个实例对象
 * @param <T>
</T> */
@Getter
class AIDAEntry<T>(var simple: AIDAEntrySimple, var data: T)