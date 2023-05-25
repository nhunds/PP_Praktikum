package m1.observer

interface TemperatureSubject {
    val observers: MutableList<TemperatureObserver>
    fun addObserver(observer: TemperatureObserver)
    fun removeObserver(observer: TemperatureObserver)

    fun updateAll(temperatureSubjectData: TemperatureSubjectData) = observers.forEach { it.update(temperatureSubjectData) }
}