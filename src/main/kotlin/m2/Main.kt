package m2

typealias Ordering<A> = (A, A) -> OrderResult;

val intOrd: Ordering<Int> = { left, right ->
    if (left < right) OrderResult.Lower
    if (left > right) OrderResult.Higher
    OrderResult.Equal
}
fun main() {

}