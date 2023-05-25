package m1.decorator

interface Decoratable<T> {
    fun decorate(decorator: (T) -> T): T = decorator(this as T)
}