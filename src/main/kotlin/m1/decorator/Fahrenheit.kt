package m1.decorator

import m1.Sensor

class Fahrenheit(sensor: Sensor) : SensorDecorator(sensor) {
    override fun getTemperature(): Double = sensor.getTemperature() * 1.8 + 32
}