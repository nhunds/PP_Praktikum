package m2

typealias Ordering<A> = (A) -> (A) -> OrderResult;

val intOrd: Ordering<Int> = { left ->
    { right ->
        if (left < right) OrderResult.Lower
        else if (left > right) OrderResult.Higher
        else OrderResult.Equal
    }
}

val stringOrd: Ordering<String> = { left ->
    { right ->
        if (left < right) OrderResult.Lower
        else if (left > right) OrderResult.Higher
        else OrderResult.Equal
    }
}

val doubleOrd: Ordering<Double> = { left ->
    { right ->
        if (left < right) OrderResult.Lower
        else if (left > right) OrderResult.Higher
        else OrderResult.Equal
    }
}

val booleanOrd: Ordering<Boolean> = { left ->
    { right ->
        if (left < right) OrderResult.Lower
        else if (left > right) OrderResult.Higher
        else OrderResult.Equal
    }
}

fun<A> Ordering<A>.reversed(): Ordering<A> = { left ->
    { right ->
        when (this(left)(right)) {
            OrderResult.Lower -> OrderResult.Higher
            OrderResult.Higher -> OrderResult.Lower
            else -> OrderResult.Equal
        }
    }
}

fun<A> Ordering<A>.debug(): Ordering<A> = { left ->
    { right ->
        val orderResult = this(left)(right)
        println("$left $orderResult $right")
        orderResult
    }
}

fun<A, B> Ordering<A>.contraMap(transform: (B) -> A): Ordering<B> = { left ->
    { right ->
        this(transform(left))(transform(right))
    }
}

fun<A, B> Ordering<A>.zip(otherOrd: Ordering<B>): Ordering<Pair<A, B>> = { left ->
    { right ->
        this(left.first)(right.first).let {
            if (it == OrderResult.Equal)
                otherOrd(left.second)(right.second)
            else it
        }
    }
}

val personNameOrd = stringOrd.contraMap { person: Person ->
    person.name
}

val personAgeOrd = intOrd.contraMap { person: Person ->
    person.age
}

fun main() {
    stringOrd
        .reversed()("hallo")("weggehen")
    doubleOrd
        .reversed()
        .debug()(0.5)(1.5)
    val people = mutableListOf(
        Person("Nathalie", 25, 172.5),
        Person("Alex", 33, 186.0),
        Person("Zah", 28, 158.3),
        Person("Alex", 18, 183.0),
        Person("Jens", 33, 168.5),
    )
    val personOrd = stringOrd
        .zip(intOrd)
        .contraMap { person: Person ->
            person.name to person.age
        }
    Sorting().sort(people, personOrd)
    people.forEach(::println)
    personNameOrd
        .debug()(people[0])(people[1])
    personAgeOrd
        .debug()(people[0])(people[1])
    stringOrd
        .zip(intOrd)
        .contraMap { person: Person ->
            person.name to person.age
        }
        .debug()(people[0])(people[1])

}