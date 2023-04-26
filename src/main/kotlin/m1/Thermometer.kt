package m1

class Thermometer(var sensor: Sensor) {
    fun measure(times: Int) = repeat(times) {
        println("Die Temperatur ist: ${sensor.getTemperature()}")
    }
}