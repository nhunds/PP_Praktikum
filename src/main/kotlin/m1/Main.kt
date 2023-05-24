package m1

import m1.decorator.Fahrenheit
import m1.decorator.RoundValue
import m1.decorator.SensorLogger
import m1.observer.HeatingSystemObserver
import m1.observer.TemperatureAlert
import m1.strategy.ConstantSensor
import m1.strategy.IncreasingSensor
import m1.strategy.RandomSensor

fun main() {
    /*
    Strategy:
    Durch das Strategy-Pattern ist die Art des Sensors im Thermometer zur Laufzeit veränderbar. So kann je nach
    Situation mit einer anderen Art von Sensor gearbeitet werden. Während sich das Thermometer nicht um die Details
    der Implementierung der jeweiligen Art des Sensors kümmern muss. Weiter ist die Menge von Arten von Sensoren
    erweiterbar.
    Erfüllt werden die Design-Prinzipien:
    - Single Responsibility: Da sich Thermometer und die jeweiligen Arten von Sensoren nur um sich kümmern müssen
    - Encapsulate what varies: Da die variierenden Arten von Sensor gekapselt werden.
    - Programm to Interface, not an implementation: Da Sensor ein Interface ist
    - Favor Composition over Inheritance: Da die Art der Sensor als austauschbare Variable im Thermometer existiert
     */
    /*
    Decorator:
    Der Decorator ermöglicht es die Funktionalität des Sensors einfach zu erweitern, z.B. um die Ausgabe auf der
    Console. Er verhindert dabei die Überfrachtung der measure-Funktion mit Parametern und ermöglicht das externe
    hinzufügen von Funktionalitäten. Dies wäre mit dem Strategy-Pattern nicht möglich gewesen. Denn dieses
    ermöglicht nur ein verändertes Basisverhalten. So hätte die Ausgabe auf der Console für jede Strategy als
    zusätzliche neue Strategy implementiert werden müssen. Auch hätte die Kombination von erweiterten Funktionalitäten
    für eine starke Zunahme an Redundanz geführt.
    Der Unterschied zwischen Decorator und Strategy liegt darin dass, eine Strategy ein neues Verhalten implementiert
    während der Decorator eine Erweiterung der Funktionalität abbildet.
    Erfüllt werden die Design-Prinzipien:
    - Single Responsibility: Da je ein Decorator für eine Funktionalität zuständig ist.
    - Open-Closed Principle: Jeder Decorator ist in sich abgeschlossen aber auf ihm kann aufgebaut werden
    - Program to an interface, not an implementation: Alle Decorator nutzen das Sensor-Interface
    - Favor Composition over Inheritance: Die Decorator beinhalten den Sensor den sie erweitern als Eigenschaft
    - Don't repeat yourself: Es wird vermieden dass, Funktionalität für jeden Sensor erneut implementiert werden muss
     */
    /*
    Observer:
    Mit dem Observer werden mehrere Probleme gelöst. Er verhindert over polling, da hier der Datenhalter bei Änderungen
    die Dateninteressierten aufruft. Verhindert eine harte Kopplung zwischen beiden. Macht die Dateninteressierten
    zur Laufzeit änderbar. Vermeidet die Anpassung des Datenhalters beim hinzukommen eines neuen Dateninteressierten.
    Würde er nicht genutzt müssten die Dateninteressierten regelmäßig beim Thermometer nachsehen. Oder Thermometer
    müsste für jeden neuen Dateninteressierten angepasst werden. Hier wäre eine Änderung der Dateninteressierten zur
    Laufzeit schwer möglich.
    Erfüllt werden die Design-Prinzipien:
    - Open-Closed Principle: Da Thermometer nicht für jeden neuen Dateninteressierten modifiziert werden muss, aber
    durch Beobachtung erweiterbar ist.
    - Program to an Interface, not an implementation: Da Observer und Subject als Interface implementiert sind
    - Favor Composition over Inheritance: Da das Subject die es beobachtenden Observer als Eigenschaft hält
    - Loose Coupling: Da die Kopplung über Interfaces erfolgt
     */
    println("Random sensor:")
    val thermometer = Thermometer(SensorLogger(RandomSensor(2.0, 8.0)))
    thermometer.measure(10)

    println("Increasing sensor:")
    thermometer.sensor = SensorLogger(IncreasingSensor(15.0))
    thermometer.measure(10)

    println("Constant sensor:")
    thermometer.sensor = SensorLogger(ConstantSensor(10.0))
    thermometer.measure(10)

    println("Rounded random sensor:")
    thermometer.sensor = SensorLogger(RoundValue(RandomSensor(2.0, 5.0)))
    thermometer.measure(10)

    println("Rounded increasing sensor in Fahrenheit")
    thermometer.sensor = SensorLogger(RoundValue(Fahrenheit(IncreasingSensor(20.0))))
    thermometer.measure(10)

    println("Rounded increasing sensor in Fahrenheit and Celsius")
    thermometer.sensor = SensorLogger(RoundValue(Fahrenheit(SensorLogger(IncreasingSensor(20.0)))))
    thermometer.measure(10)

    println("Observer")
    thermometer.sensor = SensorLogger(RoundValue(RandomSensor(10.0, 50.0)))
    val alertObserver = TemperatureAlert(
        alertTemp = 30.0,
        alertMsg = "Ganz schön heiß"
    )
    val heatingSystemObserver = HeatingSystemObserver(
        offThreshold = 23.0,
        onThreshold = 19.0
    )
    thermometer.addObserver(alertObserver)
    thermometer.addObserver(heatingSystemObserver)
    thermometer.measure(20)
}