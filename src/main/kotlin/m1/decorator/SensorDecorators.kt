package m1.decorator

import m1.Sensor

object SensorDecorators {
    val logWithPrompt: (String) -> (Sensor) -> Sensor =
        { prompt: String ->
            { sensor: Sensor ->
                SensorLogger(sensor, prompt)
            }
        }
    val logger: (Sensor) -> Sensor = logWithPrompt("")
    val debug: (Sensor) -> Sensor = logWithPrompt("DEBUG: ")
    val info: (Sensor) -> Sensor = logWithPrompt("INFO: ")
    val roundValue: (Sensor) -> Sensor = ::RoundValue
    val fahrenheit: (Sensor) -> Sensor = ::Fahrenheit
}