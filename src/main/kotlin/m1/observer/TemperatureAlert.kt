package m1.observer

class TemperatureAlert(val alertTemp: Double, val alertMsg: String): TemperatureObserver {
    override fun update(tmp: Double) {
        if (tmp >= alertTemp)
            println(alertMsg)
    }
}