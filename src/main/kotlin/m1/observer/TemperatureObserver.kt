package m1.observer

interface TemperatureObserver {
    fun update(temperatureSubjectData: TemperatureSubjectData)
}