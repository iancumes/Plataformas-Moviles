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

}