package m1

class Fahrenheit(sensor: Sensor) : SensorDecorator(sensor) {
    override fun getTemperature(): Double = sensor.getTemperature() * 1.8 + 32
}