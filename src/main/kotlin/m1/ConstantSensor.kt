package m1

class ConstantSensor(val temp: Double): Sensor {
    override fun getTemperature(): Double = temp
}