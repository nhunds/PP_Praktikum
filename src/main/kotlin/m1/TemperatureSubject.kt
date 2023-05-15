package m1

interface TemperatureSubject {
    val observers: MutableList<TemperatureObserver>
    fun addObserver(observer: TemperatureObserver)
    fun removeObserver(observer: TemperatureObserver)
}