package m1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class IncreasingSensorTest {

    @Test
    fun getTemperature() {
        val increasingSensor = IncreasingSensor(10.0)

        repeat(10) {
            assertEquals(10.0 + it, increasingSensor.getTemperature())
        }
    }
}