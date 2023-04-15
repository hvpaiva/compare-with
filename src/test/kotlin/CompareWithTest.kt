import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CompareWithTest {

    @Test
    fun `compareWith should return correct CompareOutcome`() {
        assertAll("Testing compareWith",
            { assertEquals(CompareOutcome.Less, "apple" compareWith "banana") },
            { assertEquals(CompareOutcome.Equal, 42 compareWith 42) },
            { assertEquals(CompareOutcome.Greater, 42 compareWith 24) },
            { assertEquals(CompareOutcome.Less, 0.0 compareWith 0.1) },
            { assertEquals(CompareOutcome.Greater, Double.MAX_VALUE compareWith Double.MIN_VALUE) }
        )
    }

    @Test
    fun `comparator should return correct Comparator`() {
        data class Product(val name: String, val price: Double)

        val priceComparator = ComparatorWith<Product> { a, b ->
            a.price.compareWith(b.price)
        }

        val productList = listOf(
            Product("Product A", 10.0),
            Product("Product B", 5.0),
            Product("Product C", 20.0)
        )

        val sortedByPrice = productList.sortedWith(priceComparator)

        assertAll("Testing compareUsing",
            { assertEquals(Product("Product B", 5.0), sortedByPrice[0]) },
            { assertEquals(Product("Product A", 10.0), sortedByPrice[1]) },
            { assertEquals(Product("Product C", 20.0), sortedByPrice[2]) }
        )
    }
}