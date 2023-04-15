/**
 * Represents the possible outcomes of a comparison between two values.
 */
enum class CompareOutcome(val asInt: Int) {
    Less(-1),
    Equal(0),
    Greater(1)
}

/**
 * Compares this value with the specified value for order and returns an instance of CompareOutcome.
 *
 * @receiver The value to compare
 * @param other The value to be compared with
 * @return An instance of CompareOutcome representing the comparison outcome
 */
infix fun <T : Comparable<T>> T.compareWith(other: T): CompareOutcome {
    return when (this.compareTo(other)) {
        in Int.MIN_VALUE until 0 -> CompareOutcome.Less
        0 -> CompareOutcome.Equal
        in 1..Int.MAX_VALUE -> CompareOutcome.Greater
        else -> error("Unexpected compareTo result")
    }
}

/**
 * Creates a Comparator based on a custom comparison function.
 *
 * @param comparisonFunction A function that takes two objects of type T and returns a CompareOutcome
 * @return A Comparator that takes two objects of type T and compares them using the provided comparisonFunction
 */
@Suppress("FunctionName")
fun <T> ComparatorWith(comparisonFunction: (T, T) -> CompareOutcome): Comparator<T> {
    return Comparator { a, b ->
        comparisonFunction(a, b).asInt
    }
}
