import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.exp
import java.util.Stack


object Calculator {
    // Function to check the precedence of operators
    private fun precedence(op: Char): Int {
        return when (op) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^', 'r' -> 3
            'e' -> 4
            else -> -1
        }
    }
    // Function to perform arithmetic operations
    private fun applyOp(a: Double, b: Double, op: Char): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            '^' -> a.pow(b)
            'r' -> b.pow(1 / a) // a-th root of b
            'e' -> exp(b) // exponential function e^b, a is ignored
            else -> throw UnsupportedOperationException("Operator not supported")
        }
    }



    }