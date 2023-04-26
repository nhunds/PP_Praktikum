package m1

class IncreasingSensor(var temp: Double): Sensor {
    override fun getTemperature(): Double = temp++
}