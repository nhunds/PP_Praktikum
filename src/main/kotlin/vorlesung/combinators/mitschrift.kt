package vorlesung.combinators

data class Parser<A>(val parse: (String) -> Pair<A?, String>) {
    fun <B> zip(that: Parser<B>): Parser<Pair<A, B>> = Parser { input ->
        val (matchA: A?, rest: String) = this.parse(input)
        if (matchA == null) return@Parser Pair(null, input)
        val (matchB: B?, rest2: String) = that.parse(rest)
        if (matchB == null) return@Parser Pair(null, input)
        Pair(Pair(matchA, matchB), rest2)
    }

    fun <B> map(f: (A) -> B): Parser<B> = Parser {
        input ->
        val (matchA: A?, rest: String) = this.parse(input)
        if (matchA == null) return@Parser Pair(null, input)
        val b = f(matchA)
        Pair(b, rest)
    }

    fun <B> flatMap(f: (A) -> Parser<B>): Parser<B> = Parser {input ->
        val (matchA: A?, rest: String) = this.parse(input)
        if (matchA == null) return@Parser Pair(null, input)
        val parserB = f(matchA)
        val (matchB, rest2) = parserB.parse(rest)
        if (matchB == null) return@Parser Pair(null, input)
        Pair(matchB, rest2)
    }
}
fun main() {
    val charParser: Parser<Char> = Parser { input ->
        Pair(input.firstOrNull(), input.substring(1))
    }

    println(charParser.parse("hallo"))
}