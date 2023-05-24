package m1.strategy

import m1.Sensor

class IncreasingSensor(var temp: Double): Sensor {
    override fun getTemperature(): Double = temp++
}