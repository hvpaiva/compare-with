# CompareWith — Simplified Comparisons in Kotlin

CompareWith is a small Kotlin library that simplifies comparisons by introducing a more 
expressive and idiomatic way to compare `Comparable` objects. It provides a `CompareOutcome` 
enum and a set of utility functions to easily implement custom comparison logic in your 
Kotlin projects.

## Features

- Enum-based comparison outcomes (`Less`, `Equal`, `Greater`)
- `compareWith` extension function for `Comparable` types
- `ComparatorWith` extension function for creating custom `Comparator` instances based on comparison functions

## Usage

### Using `compareWith`
```kotlin
val result = "apple".compareWith("banana") 
// result: CompareOutcome.Less

val numberComparison = 42 compareWith 24 
// result: CompareOutcome.Greater
```

### Implementing a custom `compareTo` method
```kotlin
data class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    companion object {
        private val personComparator = ComparatorWith<Person> { a, b ->
            when (val lastNameComparison = a.lastName.compareWith(b.lastName)) {
                CompareOutcome.Equal -> a.firstName.compareWith(b.firstName)
                else -> lastNameComparison
            }
        }
    }

    override fun compareTo(other: Person): Int {
        return personComparator.compare(this, other)
    }
}

```

### Creating a custom `Comparator`
```kotlin
data class Product(val name: String, val price: Double)

val priceComparator = ComparatorWith<Product> { a, b ->
    a.price compareWith b.price
}

val productList = listOf(
    Product("Product A", 10.0),
    Product("Product B", 5.0),
    Product("Product C", 20.0)
)

val sortedByPrice = productList.sortedWith(priceComparator)
```

> **Note:** The `ComparatorWith` function has similar use with the SAM `Comparator`. The difference is
> their construction. While the Comparator expects `(T, T) -> Int`, the `ComparatorWith` expects
> the `(T, T) -> CompareOutcome`. But both create a `Comparator` with single abstract method `#compare(T, T): Int`.


## When to use and when not to use

In most cases, you should use the comparison operators (`<`, `>`, `<=`, `>=`, `==`) when performing 
comparisons in Kotlin. The **CompareWith** library is designed to help when implementing custom 
comparison logic, such as when implementing the `compareTo` method in a class that extends 
`Comparable`.

The primary advantage of this library lies in its expressiveness, as using the enums `Less`, 
`Equal`, and `Greater` provides a clearer and more intuitive understanding compared to the 
integers `-1`, `0`, and `1`.

## Installation

Using gradle with Kotlin DSL:
```kotlin
dependencies {
    // ...
    implementation("dev.hvpaiva:compare-with:1.0.0")
}
```

Using Maven:
```xml
<dependency>
    <groupId>dev.hvpaiva</groupId>
    <artifactId>compare-with</artifactId>
    <version>1.0.0</version>
</dependency>
```