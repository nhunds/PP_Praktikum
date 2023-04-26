package m1

class ConstantSensor(val temperature: Double): Sensor {
    override fun getTemperature(): Double = temperature
}