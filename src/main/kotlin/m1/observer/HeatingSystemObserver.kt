package m1.observer

import m1.Thermometer

class HeatingSystemObserver(
    private val onThreshold: Double,
    private val offThreshold: Double
) : Observer<Thermometer> {
    private val temperatures = mutableListOf<Double>()

    override fun update(observableData: Observable.Data<Thermometer>) {
        val data = observableData as Thermometer.ThermometerData
        temperatures.add(data.temp)

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