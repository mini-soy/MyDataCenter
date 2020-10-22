package name.soy.dc.device.aida
/**
 * AIDA一个实例对象
 * @param <T>
</T> */
data class AIDAEntry<T>(var simple: AIDAEntrySimple, var data: T)