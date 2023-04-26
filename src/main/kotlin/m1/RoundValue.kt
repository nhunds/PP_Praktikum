package m1

import kotlin.math.roundToInt

class RoundValue(sensor: Sensor) : SensorDecorator(sensor) {
    override fun getTemperature(): Double = sensor.getTemperature().roundToInt().toDouble()
}