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

fun<A, B> Ordering<A>.contraMap(transform: (B) -> A): Ordering<B> = { left, right ->
    this(transform(left), transform(right))
}

val personNameOrd = stringOrd.contraMap { person: Person ->
    person.name
}

val personAgeOrd = intOrd.contraMap { person: Person ->
    person.age
}

fun main() {
    val intDesc = reversed(intOrd)
    val string = debug(stringOrd)
    string("hallo", "weggehen")
    val doubleDesc = debug(reversed(doubleOrd))
    doubleDesc(0.5, 1.5)
    val person1 = Person("Max Muster", 40)
    val person2 = Person("Melanie Müller", 30)
    debug(personNameOrd)(person1, person2)
    debug(personAgeOrd)(person1, person2)
}