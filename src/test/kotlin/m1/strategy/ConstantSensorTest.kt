package m1.strategy

import m1.strategy.ConstantSensor
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ConstantSensorTest {

    @Test
    fun getTemperature() {
        val constantSensor = ConstantSensor(10.0)

        repeat(10) {
            assertEquals(10.0, constantSensor.getTemperature())
        }
    }
}