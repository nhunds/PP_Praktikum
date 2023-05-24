package m1.decorator

import m1.Sensor
import kotlin.math.roundToInt

class RoundValue(sensor: Sensor) : SensorDecorator(sensor) {
    override fun getTemperature(): Double = sensor.getTemperature().roundToInt().toDouble()
}