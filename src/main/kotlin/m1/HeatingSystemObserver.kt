package m1

class HeatingSystemObserver(
    private val minTemp: Double,
    private val maxTemp: Double
) : TemperatureObserver {
    private val temperatures = mutableListOf<Double>()

    override fun update(tmp: Double) {
        temperatures.add(tmp)

        if (temperatures.size == 5) {
            val avgTemp = temperatures.sum() / temperatures.size

            if (avgTemp < minTemp)
                println("Heizung an")
            else if (avgTemp > maxTemp)
                println("Heizung aus")
        }
    }
}