package m1.observer

import m1.Sensor

class Thermometer(var sensor: Sensor) : TemperatureSubject {
    override val observers = mutableListOf<TemperatureObserver>()
    fun measure(times: Int) = repeat(times) {
        val temp = sensor.getTemperature()

        observers.forEach {
            it.update(temp)
        }
    }

    override fun addObserver(observer: TemperatureObserver) {
        observers.add(observer)
    }
    override fun removeObserver(observer: TemperatureObserver) {
        observers.remove(observer)
    }
}