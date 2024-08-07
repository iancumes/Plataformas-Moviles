package edu.uvg.ian.primerprograma

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

            else -> throw UnsupportedOperationException("Operator not supported")
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
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == 'r') {
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

class MainActivity : AppCompatActivity() {

    private var expression: StringBuilder = StringBuilder()
    private lateinit var txtScreen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtScreen = findViewById(R.id.txtScreen)

        // Set listeners for buttons
        setListeners()
    }

    private fun setListeners() {
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnDot, R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnPower, R.id.btnRoot, R.id.btnOpenParenthesis, R.id.btnCloseParenthesis
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { onButtonClick(it) }
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { onEqualsClick() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { onClearClick() }
    }

    private fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btn0 -> appendExpression("0")
            R.id.btn1 -> appendExpression("1")
            R.id.btn2 -> appendExpression("2")
            R.id.btn3 -> appendExpression("3")
            R.id.btn4 -> appendExpression("4")
            R.id.btn5 -> appendExpression("5")
            R.id.btn6 -> appendExpression("6")
            R.id.btn7 -> appendExpression("7")
            R.id.btn8 -> appendExpression("8")
            R.id.btn9 -> appendExpression("9")
            R.id.btnDot -> appendExpression(".")
            R.id.btnAdd -> appendExpression("+")
            R.id.btnSubtract -> appendExpression("-")
            R.id.btnMultiply -> appendExpression("*")
            R.id.btnDivide -> appendExpression("/")
            R.id.btnPower -> appendExpression("^")
            R.id.btnRoot -> appendExpression("r")
            R.id.btnOpenParenthesis -> appendExpression("(")
            R.id.btnCloseParenthesis -> appendExpression(")")
        }
    }

    private fun appendExpression(value: String) {
        expression.append(value)
        updateScreen()
    }

    private fun updateScreen() {
        txtScreen.text = expression.toString()
    }

    private fun onEqualsClick() {
        try {
            val result = Calculator.evaluate(expression.toString())
            txtScreen.text = result.toString()
            expression.clear()
            expression.append(result)
        } catch (e: Exception) {
            txtScreen.text = "Error"
            expression.clear()
        }
    }

    private fun onClearClick() {
        expression.clear()
        updateScreen()
    }
}