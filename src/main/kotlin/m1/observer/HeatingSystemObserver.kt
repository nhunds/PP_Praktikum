package m1.observer

class HeatingSystemObserver(
    private val onThreshold: Double,
    private val offThreshold: Double
) : TemperatureObserver {
    private val temperatures = mutableListOf<Double>()

    override fun update(temperatureSubjectData: TemperatureSubjectData) {
        val tmp = temperatureSubjectData.temp
        temperatures.add(tmp)

        if (temperatures.size == 5) {
            val avgTemp = temperatures.sum() / temperatures.size

            if (avgTemp < onThreshold)
                println("Heizung an")
            else if (avgTemp > offThreshold)
                println("Heizung aus")
            temperatures.clear()
        }
    }
}