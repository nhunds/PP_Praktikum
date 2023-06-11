package vorlesung.functional

import kotlin.random.Random

// Abstrakte Strategien
interface Movement {
    fun move(): Int
}

interface Weapon {
    val name: String
    fun doDamage(): Int
}

class Player(
    val name: String,
    var movement: Movement,
    var weapon: Weapon,
    var health: Int,
    val onDamageTaken: (Player) -> Unit
) {
    override fun toString(): String = name

    fun move(){
        println("$name bewegt sich ${movement.move()} Felder weit")
    }

    fun attack(target: Player){
        println("$name greift mit ${weapon.name} an und macht ${weapon.doDamage()} Schaden")
        target.takeDamage(weapon.doDamage())
    }

    fun takeDamage(damage: Int) {
        health -= damage
        if(health > 0)
            println("$name hat noch $health HP")
        onDamageTaken(this)
    }

    fun applyAOE(f: (Int) -> Int): (List<Player>) -> Unit = { targets ->
        for ((index, target) in targets.withIndex()) {
            val damage = f(weapon.doDamage()) * (targets.size / (index + 1))
            target.takeDamage(damage)
        }
    }
}

// Konkrete Strategien
class SlowMovement(): Movement {
    override fun move() = 5
}

class FastMovement(): Movement {
    override fun move() = 10
}

class WoodSword(): Weapon {
    override val name: String
        get() = "Holzschwert"

    override fun doDamage() = 3
}

class IronSword(): Weapon {
    override val name: String
        get() = "Eisenschwert"
    override fun doDamage() = 10
}

class MasterSword(): Weapon {
    override val name: String
        get() = "Masterschwert"
    override fun doDamage() = 30
}

// Dekorierer
class FireEnchantment(val weapon: Weapon): Weapon {
    override val name: String = "🔥${weapon.name}🔥"
    override fun doDamage() = weapon.doDamage() * 2
}

class BrokenWeapon(val weapon: Weapon): Weapon {
    override val name: String = "${weapon.name} (Kaputt)"
    override fun doDamage() = (weapon.doDamage() * 0.3).toInt()
}


// Kompositum
class Ultimate(val weapons: List<Weapon>): Weapon {
    override val name: String
        get() = "Ultimative Waffe bestehend aus: ${weapons.joinToString(", ") { it.name }}"

    override fun doDamage() = weapons.sumOf { it.doDamage() } * 3

}

// fun<A, B, C> compose(f: (A) -> B, g: (B) -> C): (A) -> C =
//     { a: A ->
//         g(f(a))
//     }

infix fun<A, B, C> ((A) -> B).compose(g: (B) -> C): (A) -> C =
    { a: A ->
        g(this(a))
    }
class Game(var players: MutableList<Player>) {

    fun playerWithHighestDmg() =
        players.maxByOrNull { it.weapon.doDamage() }

    val getFragmentDamage: (Int) -> Int =
        { dmg -> (dmg * 0.1).toInt() }

    val criticalStrike: (Int) -> Int = {
        damage ->
        if (Random.nextBoolean()) (damage * 1.5).toInt() else damage
    }

    val miss: (Float) -> (Int) -> Int = {
        misschance: Float -> {
            damage: Int ->
            if (Random.nextInt(0, 100) <= misschance * 100) 0 else damage
        }
    }

    fun filterPlayers(p: (Player) -> Boolean): List<Player> {
        val filtered = mutableListOf<Player>()
        for (player in players) {
            if(p(player))
                filtered += player
        }
        return filtered
    }

    fun topPlayers() = filterPlayers { it.health > 10 }

    fun play() {
        players[0].move()
        players[0].attack(players[1])
        println("----------")

        players[1].move()
        players[1].attack(players[0])
        println("----------")

        players[0].weapon = MasterSword()
        players[0].attack(players[1])
        players[0].applyAOE(getFragmentDamage compose criticalStrike compose miss(0.3F))(
            listOf(players[1], players[2], players[3])
        )
        players[0]
        println("----------")
    }
}

fun printIfLowHealth(player: Player) {
    if (player.health in 1..10) {
        println("${player.name} ist im kritischen Bereich!")
    }
}

fun main(){
    val player1 = Player("Link", FastMovement(), IronSword(), 15, ::printIfLowHealth)
    val player2 = Player("Ganondorf", SlowMovement(), IronSword(), 25, ::printIfLowHealth)
    val add1 = Player("Bokoblin", SlowMovement(), WoodSword(), 5, ::printIfLowHealth)
    val add2 = Player("Moblin", SlowMovement(), WoodSword(), 5, ::printIfLowHealth)

    val game = Game(mutableListOf(player1, player2, add1, add2))

    game.play()
    game.topPlayers().forEach(::println)
    game.playerWithHighestDmg().also(::println)
}