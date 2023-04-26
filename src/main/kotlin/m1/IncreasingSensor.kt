package m1

class IncreasingSensor(var temperature: Double): Sensor {
    override fun getTemperature(): Double = temperature++
}