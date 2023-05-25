package m1

import m1.decorator.Decoratable
import m1.decorator.SensorLogger

interface Sensor: Decoratable<Sensor> {
    fun getTemperature(): Double
}