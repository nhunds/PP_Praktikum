package m1

import m1.observer.TemperatureObserver
import m1.observer.TemperatureSubject
import m1.observer.TemperatureSubjectData

class Thermometer(var sensor: Sensor) : TemperatureSubject {
    override val observers = mutableListOf<TemperatureObserver>()
    fun measure(times: Int) = repeat(times) {
        val temp = sensor.getTemperature()

        updateAll(TemperatureSubjectData(temp))
    }

    override fun addObserver(observer: TemperatureObserver) {
        observers.add(observer)
    }
    override fun removeObserver(observer: TemperatureObserver) {
        observers.remove(observer)
    }
}