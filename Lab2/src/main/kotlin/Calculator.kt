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

fun infixToPostfix(expression: String): String {
    val result = StringBuilder()
    val stack = Stack<Char>()
    var i = 0
    while (i < expression.length) {
        val c = expression[i]

        // If the character is an operand, add it to output
        if (c.isDigit() || c == '.') {
            while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                result.append(expression[i++])
            }
            result.append(' ')
            i--
        } else if (c == '(') {
            stack.push(c)
        } else if (c == ')') {
            while (stack.isNotEmpty() && stack.peek() != '(') {
                result.append(stack.pop()).append(' ')
            }
            stack.pop()
        } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == 'e' || c == 'r') {
            while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(c)) {
                result.append(stack.pop()).append(' ')
            }
            stack.push(c)
        }
        i++
    }

    // Pop all the operators from the stack
    while (stack.isNotEmpty()) {
        result.append(stack.pop()).append(' ')
    }

    return result.toString()
}

fun evaluatePostfix(expression: String): Double {
    val stack = Stack<Double>()
    val tokens = expression.split(" ")
    for (token in tokens) {
        if (token.isEmpty()) continue
        if (token[0].isDigit() || token[0] == '.') {
            stack.push(token.toDouble())
        } else {
            val b = stack.pop()
            val a = if (stack.isNotEmpty() && token[0] != 'e') stack.pop() else 0.0 // Default a to 0 if stack is empty and not 'e'
            stack.push(applyOp(a, b, token[0]))
        }
    }
    return stack.pop()
}

// Function to evaluate infix expression
fun evaluate(expression: String): Double {
    return evaluatePostfix(infixToPostfix(expression))
    }
}

