package vorlesung.functional

fun main() {
    val func = {x: Int, y: Int -> x + y}
    val funcResult = func.invoke(5, 3)  // func(5, 3)
    println(funcResult)

    class Parser(val parse: (String) -> Int)
    val p = Parser({input -> input.toInt()}) // Parser { input -> input.toInt() }
    p.parse("5")

    // Metafunktion
    fun<A> twice(f: (A) -> A): (A) -> A = { x: A -> f(f(x)) }

    fun incr(x: Int): Int = x + 1
    val incrTwice = twice(::incr)
    incrTwice(2)    // 4

    fun quad(x: Int): Int = x * x
    val quadTwice = twice(::quad)
    quadTwice(2)    // 16

    fun shiftByChar(password: String): String =
        password.map { it + 1 }.joinToString("")

    val strongPassword = twice(twice(twice(::shiftByChar)))
    println(strongPassword("Test123"))

    fun isSameNumber(text: String, value: Int): Boolean {
        val textValue = text.toIntOrNull() ?: return false
        return textValue == value
    }

    fun<A, B, C> flip(f: (A, B) -> C): (B, A) -> C = { b, a -> f(a, b) }
    val flippedIsSameNumber = flip(::isSameNumber)
    flippedIsSameNumber(18, "39")
}