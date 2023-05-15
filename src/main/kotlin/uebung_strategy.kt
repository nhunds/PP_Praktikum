/*
In einer kleinen Videospiel Simulation soll ein Spieler durch unterschiedliche Strategien laufen und angreifen können
Implementiert dazu die abstrakte Strategie Movement mit der Funktion move(): Int. Die Funktion gibt die Bewegung
in Feldern zurück. Dazu soll es die die folgenden konkreten Strategien geben:
- SlowMovement (Eine Bewegung um 5 Felder)
- FastMovement (Eine Bewegung um 10 Felder)
Des Weiteren soll es die abstrakte Strategie Weapon geben, die den Namen der Waffe als Eigenschaft und eine
Funktion doDamage(): Int vorgibt. Die Funktion gibt den Schaden der Waffe zurück. Implementiert dazu die
folgenden konkreten Strategien:
- WoodSword (Ein Holzschwert mit einem Schaden von 3)
- IronSword (Ein Eisenschwert mit einem Schaden von 10)
- MasterSword (Ein Masterschwert mit einem Schaden von 30)
Testet eure Implementierung mit dem folgenden Code. Tauscht die jeweiligen Strategien in der main Funktion aus:
*/
interface Movement {
    fun move(): Int
}

class SlowMovement: Movement {
    override fun move(): Int = 5
}

class FastMovement: Movement {
    override fun move(): Int = 10
}

interface Weapon {
    val name: String
    fun doDamage(): Int
}

class WoodSword: Weapon {
    override val name: String
        get() = "wood sword"
    override fun doDamage(): Int = 3
}

class IronSword: Weapon {
    override val name: String
        get() = "iron sword"
    override fun doDamage(): Int = 10
}

class MasterSword: Weapon {
    override val name: String
        get() = "master sword"
    override fun doDamage(): Int = 30
}

abstract class WeaponDecorator : Weapon {
    protected abstract val weapon: Weapon
}

class FireEnchantment(override val weapon: Weapon) : WeaponDecorator() {
    override val name: String
        get() = "fire enchanted ${weapon.name}"

    override fun doDamage(): Int = weapon.doDamage() * 2
}

class BrokenWeapon(override val weapon: Weapon) : WeaponDecorator() {
    override val name: String
        get() = "broken ${weapon.name}"

    override fun doDamage(): Int = (weapon.doDamage() * 0.3).toInt()
}

abstract class WeaponCompositor : Weapon {
    protected abstract val weapons: List<Weapon>
}

class Ultimate(override val weapons: List<Weapon>) : WeaponCompositor() {
    override val name: String
        get() {
            val size = weapons.size

            if(size < 2)
                return weapons.joinToString { it.name }
            else {
                val lastIndex = size - 1
                return weapons.foldIndexed("") { index, acc, weapon ->
                    if (index == lastIndex)
                        acc + weapon.name
                    else if (index == lastIndex - 1)
                        acc + weapon.name + " und "
                    else
                        acc + weapon.name + ", "
                }
            }
        }
        // = weapons.joinToString { it.name }

    override fun doDamage(): Int = weapons.sumOf { it.doDamage() }
}

class Player(
    val name: String,
    var movement: Movement,
    var weapon: Weapon
) {
    fun move() {
        println("$name bewegt sich ${movement.move()} Felder weit")
    }

    fun attack() {
        println("$name greift mit ${weapon.name} an und macht ${weapon.doDamage()} Schaden")
    }
}

fun main() {
    val player1 = Player("Link", FastMovement(), WoodSword())
    val player2 = Player("Ganondorf", SlowMovement(), IronSword())

    val fireEnchantedIronSword = FireEnchantment(IronSword())
    val ultimate = Ultimate(listOf(WoodSword(), IronSword(), MasterSword(), fireEnchantedIronSword))
    val player3 = Player("Boss", SlowMovement(), ultimate)

    player1.move()
    player1.attack()

    player2.move()
    player2.attack()

    player1.weapon = MasterSword()
    player1.attack()

    player3.attack()
}