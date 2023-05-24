package m1

import m1.decorator.Fahrenheit
import m1.decorator.RoundValue
import m1.decorator.SensorLogger
import m1.strategy.ConstantSensor
import m1.strategy.IncreasingSensor
import m1.strategy.RandomSensor

fun main() {
    println("Random sensor:")
    val thermometer = Thermometer(SensorLogger(RandomSensor(2.0, 8.0)))
    thermometer.measure(10)

    println("Increasing sensor:")
    thermometer.sensor = SensorLogger(IncreasingSensor(15.0))
    thermometer.measure(10)

    println("Constant sensor:")
    thermometer.sensor = SensorLogger(ConstantSensor(10.0))
    thermometer.measure(10)

    println("Rounded random sensor:")
    thermometer.sensor = SensorLogger(RoundValue(RandomSensor(2.0, 5.0)))
    thermometer.measure(10)

    println("Rounded increasing sensor in Fahrenheit")
    thermometer.sensor = SensorLogger(RoundValue(Fahrenheit(IncreasingSensor(20.0))))
    thermometer.measure(10)

    println("Rounded increasing sensor in Fahrenheit and Celsius")
    thermometer.sensor = SensorLogger(RoundValue(Fahrenheit(SensorLogger(IncreasingSensor(20.0)))))
    thermometer.measure(10)
}