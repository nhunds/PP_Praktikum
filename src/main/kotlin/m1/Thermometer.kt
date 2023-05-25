package m1

import m1.observer.*

class Thermometer(var sensor: Sensor) : Observable<Thermometer> {
    override val observers: MutableList<Observer<Thermometer>> = mutableListOf()
    data class ThermometerData(val temp: Double): Observable.Data<Thermometer>()
    fun measure(times: Int) = repeat(times) {
        val temp = sensor.getTemperature()

        updateAll(ThermometerData(temp))
    }
}