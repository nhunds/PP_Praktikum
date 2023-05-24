package m1.strategy

import m1.Sensor

class ConstantSensor(val temp: Double): Sensor {
    override fun getTemperature(): Double = temp
}