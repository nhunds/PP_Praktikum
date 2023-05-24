package m1.decorator

import m1.Sensor

class SensorLogger(sensor: Sensor) : SensorDecorator(sensor) {
    override fun getTemperature(): Double {
        val temp = sensor.getTemperature()

        println("Die Temperatur ist: $temp")

        return temp
    }
}