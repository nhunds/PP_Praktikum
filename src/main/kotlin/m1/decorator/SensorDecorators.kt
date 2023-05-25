package m1.decorator

import m1.Sensor

object SensorDecorators {
    val logger: (Sensor) -> Sensor = ::SensorLogger
    val roundValue: (Sensor) -> Sensor = ::RoundValue
    val fahrenheit: (Sensor) -> Sensor = ::Fahrenheit
}