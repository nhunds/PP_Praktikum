package m1.strategy

import m1.Sensor
import kotlin.random.Random

class RandomSensor(val min: Double, val max: Double) : Sensor {
    override fun getTemperature(): Double = Random.nextDouble(min, max)
}