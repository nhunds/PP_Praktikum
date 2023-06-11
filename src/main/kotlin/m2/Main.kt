package m2

typealias Ordering<A> = (A, A) -> OrderResult;

val intOrd: Ordering<Int> = { left, right ->
    if (left < right) OrderResult.Lower
    if (left > right) OrderResult.Higher
    OrderResult.Equal
}

val stringOrd: Ordering<String> = { left, right ->
    if (left < right) OrderResult.Lower
    if (left > right) OrderResult.Higher
    OrderResult.Equal
}

val doubleOrd: Ordering<Double> = { left, right ->
    if (left < right) OrderResult.Lower
    if (left > right) OrderResult.Higher
    OrderResult.Equal
}

val booleanOrd: Ordering<Boolean> = { left, right ->
    if (left < right) OrderResult.Lower
    if (left > right) OrderResult.Higher
    OrderResult.Equal
}

fun<A> reversed(ordering: Ordering<A>): Ordering<A> = { left, right ->
    when (ordering(left, right)) {
        OrderResult.Lower -> OrderResult.Higher
        OrderResult.Higher -> OrderResult.Lower
        else -> OrderResult.Equal
    }
}

fun main() {
    val intDesc = reversed(intOrd)
}