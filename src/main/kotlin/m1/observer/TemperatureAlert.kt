package m1.observer

class TemperatureAlert(val alertTemp: Double, val alertMsg: String): TemperatureObserver {
    override fun update(temperatureSubjectData: TemperatureSubjectData) {
        if (temperatureSubjectData.temp >= alertTemp)
            println(alertMsg)
    }
}