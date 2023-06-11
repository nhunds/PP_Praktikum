package m2

typealias Ordering<A> = (A, A) -> OrderResult;

val intOrd: Ordering<Int> = { left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

val stringOrd: Ordering<String> = { left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

val doubleOrd: Ordering<Double> = { left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

val booleanOrd: Ordering<Boolean> = { left, right ->
    if (left < right) OrderResult.Lower
    else if (left > right) OrderResult.Higher
    else OrderResult.Equal
}

fun<A> reversed(ordering: Ordering<A>): Ordering<A> = { left, right ->
    when (ordering(left, right)) {
        OrderResult.Lower -> OrderResult.Higher
        OrderResult.Higher -> OrderResult.Lower
        else -> OrderResult.Equal
    }
}

fun<A> debug(ordering: Ordering<A>): Ordering<A> = { left, right ->
    val orderResult = ordering(left, right)
    println("$left $orderResult $right")
    orderResult
}

fun main() {
    val intDesc = reversed(intOrd)
    val string = debug(stringOrd)
    string("hallo", "weggehen")
    val doubleDesc = debug(reversed(doubleOrd))
    doubleDesc(0.5, 1.5)
}