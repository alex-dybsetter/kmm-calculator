package com.example.calculator

class Calculator {

	enum class Operation(val symbol: String) {
		ADD("+"),
		SUBTRACT("-"),
		MULTIPLY("x"),
		DIVIDE("/"),
	}

	fun calculate(expression: String): String {
		return calculateOrderOfOperations(expression)
	}

	private fun calculateOrderOfOperations(expression: String): String {
		// Order of operations: PEMDAS
		// Parenthesis > Exponents > Multiplication || Division > Addition || Subtraction
		// Ex: 2 + (1 * 5)^2 - 4 / 2
		// --> 2 + (5)^2 - 4 / 2
		// --> 2 + 25 - 4 / 2
		// --> 2 + 25 - 2
		// --> 27 - 2
		// --> 25

		// TODO: Implement this

		return expression
	}

	private fun calculate(num1: Double, num2: Double, operation: Operation): Double {
		if (num1 == 0.0 && num2 == 0.0) return 0.0
		return when (operation) {
			Operation.ADD -> add(num1, num2)
			Operation.SUBTRACT -> subtract(num1, num2)
			Operation.MULTIPLY -> multiply(num1, num2)
			Operation.DIVIDE -> divide(num1, num2)
		}
	}

	private fun add(augend: Double, addend: Double): Double {
		return augend + addend
	}

	private fun subtract(minuend: Double, subtrahend: Double): Double {
		return minuend - subtrahend
	}

	private fun multiply(multiplicand: Double, multiplier: Double): Double {
		return multiplicand * multiplier
	}

	private fun divide(dividend: Double, divisor: Double): Double {
		return dividend / divisor
	}
}