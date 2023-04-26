package m1

import kotlin.random.Random

class RandomSensor(val min: Double, val max: Double) : Sensor {
    override fun getTemperature(): Double = Random.nextDouble(min, max)
}