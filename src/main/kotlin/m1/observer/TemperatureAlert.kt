package m1.observer

class TemperatureAlert(val alertTemp: Double): TemperatureObserver {
    override fun update(tmp: Double) {
        if (tmp >= alertTemp)
            println("Ganz schön heiß hier")
    }
}