package io.jobtrends.jobtrends.wrappers

class Wrapper {
    val instances: MutableMap<Any, Any> = mutableMapOf()

    inline fun <reified T> register(obj: T, new: Boolean? = false) {
        if (!instances.containsKey(T::class) || new == true) {
            instances[T::class] = obj as Any
        }
    }

    inline fun <reified T> getInstance(): T {
        return try {
            instances[T::class] as T
        } catch (exception: Exception) {
            val obj = T::class.java.newInstance()
            register(obj)
            obj
        }
    }
}
