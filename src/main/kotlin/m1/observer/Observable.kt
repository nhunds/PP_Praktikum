package m1.observer

interface Observable<T> {
    val observers: MutableList<Observer<T>>

    abstract class Data<T>
    fun addObserver(observer: Observer<T>) = observers.add(observer)
    fun removeObserver(observer: Observer<T>) = observers.remove(observer)
    fun updateAll(data: Data<T>) = observers.forEach { it.update(data) }
}