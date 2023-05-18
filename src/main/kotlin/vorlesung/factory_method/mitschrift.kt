package vorlesung.factory_method

import java.util.Optional

enum class Currency {
    eur, usd;

    override fun toString(): String {
        return when (this) {
            eur -> "€"
            usd -> "$"
        }
    }
    
    companion object {
        fun currencyFromSymbol(symbol: String): Optional<Currency> {
            return when (symbol) {
                "$" -> Optional.of(usd)
                "€" -> Optional.of(eur)
                else -> Optional.empty()
            }
        }
        fun fromLabel(label: String): Optional<Currency> {
            return when (label) {
                "Dollar" -> Optional.of(usd)
                "Euro" -> Optional.of(eur)
                else -> Optional.empty()
            }
        }
    }
}

fun main() {
    val eur = Currency.currencyFromSymbol("€").get()
    val usd = Currency.currencyFromSymbol("$").get()
    println(eur)
    println(usd)
    val eur2 = Currency.currencyFromSymbol(eur.toString()).get()
    val usd2 = Currency.currencyFromSymbol(usd.toString()).get()
    println(eur2)
    println(usd2)
}