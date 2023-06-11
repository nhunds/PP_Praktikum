package m1.decorator

import m1.Sensor

class SensorLogger(sensor: Sensor, var prompt: String = "") : SensorDecorator(sensor) {
    override fun getTemperature(): Double {
        val temp = sensor.getTemperature()

        println("${prompt}Die Temperatur ist: $temp")

        return temp
    }
}