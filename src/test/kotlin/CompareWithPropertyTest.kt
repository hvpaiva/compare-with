import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll

class CompareWithPropertyTest : StringSpec({

    "compareWith should satisfy reflexivity for strings" {
        checkAll(Arb.string()) { str ->
            val result = str compareWith str
            result shouldBe CompareOutcome.Equal
        }
    }

    "compareWith should satisfy reflexivity for integers" {
        checkAll(Arb.int()) { int ->
            val result = int compareWith int
            result shouldBe CompareOutcome.Equal
        }
    }

    "compareWith should satisfy transitivity for integers" {
        checkAll(Arb.int(), Arb.int(), Arb.int()) { a, b, c ->
            val a_b = a compareWith b
            val b_c = b compareWith c
            val a_c = a compareWith c

            if (a_b == CompareOutcome.Less && b_c == CompareOutcome.Less) {
                a_c shouldBe CompareOutcome.Less
            }

            if (a_b == CompareOutcome.Greater && b_c == CompareOutcome.Greater) {
                a_c shouldBe CompareOutcome.Greater
            }
        }
    }
})