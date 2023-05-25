package m1.observer

import m1.Thermometer

class TemperatureAlert(val alertTemp: Double, val alertMsg: String): Observer<Thermometer> {
    override fun update(observableData: Observable.Data<Thermometer>) {
        val data = observableData as Thermometer.ThermometerData
        if (data.temp >= alertTemp)
            println(alertMsg)
    }
}