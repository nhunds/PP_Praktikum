package m1

class SensorLogger(sensor: Sensor) : SensorDecorator(sensor) {
    override fun getTemperature(): Double {
        val temp = sensor.getTemperature()

        println("Die Temperatur ist: $temp")

        return temp
    }
}