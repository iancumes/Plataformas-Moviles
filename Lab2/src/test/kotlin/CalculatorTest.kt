import kotlin.test.Test
import kotlin.test.assertEquals
import java.util.EmptyStackException
import kotlin.math.exp
import java.util.Stack
class CalculatorTest {
    @Test
    fun testAddition() {
        val result = Calculator.evaluate("3 + 5")
        assertEquals(8.0, result)
    }
    @Test
    fun testSubtraction() {
        val result = Calculator.evaluate("10 - 2")
        assertEquals(8.0, result)
    }
    @Test
    fun testMultiplication() {
        val result = Calculator.evaluate("4 * 2")
        assertEquals(8.0, result)
    }
    @Test
    fun testDivision() {
        val result = Calculator.evaluate("16 / 2")
        assertEquals(8.0, result)
    }

    @Test
    fun testPower() {
        val result = Calculator.evaluate("2 ^ 3")
        assertEquals(8.0, result)
    }

}

@Test
fun testRoot() {
    val result = Calculator.evaluate("2 r 16")
    assertEquals(4.0, result)
}
