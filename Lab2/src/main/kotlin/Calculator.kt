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



    }