package m2

enum class OrderResult {
    Lower,
    Higher,
    Equal;

    override fun toString(): String = when (this) {
        Lower -> "is lower then"
        Higher -> "is higher then"
        Equal -> "is equal to"
    }
}