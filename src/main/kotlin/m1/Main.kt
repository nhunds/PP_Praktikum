package m1

fun main() {
    val thermometer = Thermometer(RandomSensor(2.0, 8.0))
    thermometer.measure(10)
}