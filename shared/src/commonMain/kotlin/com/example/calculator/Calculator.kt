package com.example.calculator

import com.example.calculator.dataValidation.formatCalculation
import com.example.calculator.extensions.deleteLast
import com.example.calculator.extensions.operationsRegex
import com.example.calculator.extensions.removeTrailingDecimals
import com.example.calculator.extensions.truncateTrailingPointZero

class Calculator {

	enum class Operation(val symbol: String) {
		ADD(ADD_BUTTON),
		SUBTRACT(SUBTRACT_BUTTON),
		MULTIPLY(MULTIPLY_BUTTON),
		DIVIDE(DIVIDE_BUTTON),
	}

	fun getArithmeticOperators(): Array<String> {
		return arrayOf(
			Operation.DIVIDE.symbol,
			Operation.MULTIPLY.symbol,
			Operation.ADD.symbol,
			Operation.SUBTRACT.symbol,
		)
	}

	fun calculate(expression: String): String {
		return calculateOrderOfOperations(expression)
	}

	private fun calculateOrderOfOperations(expression: String): String {
		println(expression)

		fun createWithDelimiterRegex(delimiter: Any): Regex {
			return "(?<=[$delimiter])|(?=[$delimiter])".toRegex()
		}

		fun isValidNumber(numberString: String) : Boolean {
			return numberString.isNotEmpty() && numberString.matches("\\d*\\.?\\d*".toRegex())
		}

		// When the last input was a percentage,
		// Then delete the percentage symbol and calculate
		if (expression.last().toString() == PERCENT_BUTTON) {
			return percentage(expression.deleteLast())
		}

		// Order of operations: PEMDAS
		// Parenthesis > Exponents > Multiplication || Division > Addition || Subtraction
		// Ex: 2 + (1 * 5)^2 - 4 / 2
		// --> 2 + (5)^2 - 4 / 2
		// --> 2 + 25 - 4 / 2
		// --> 2 + 25 - 2
		// --> 27 - 2
		// --> 25

		// todo find the inner most parenthesis

		// When the expression has parenthesis
		// Then calculate the value inside the innermost parenthesis first

		// todo handle exponents

		// When the expression has a ^
		// Then apply the following number as an exponent

		val regex = createWithDelimiterRegex(operationsRegex)
		val values = expression.split(regex)
		for (i in values.indices) {

			val operation = values[i]

			// Determine what operation to perform first, ignore non-operators
			if (!operation.matches(operationsRegex)) continue

			// If the operation is the last character, we can't calculate anything
			if (i == values.lastIndex) break

			// Get the numbers to operate on and validate data integrity,
			// otherwise abort calculation
			if (!isValidNumber(values[i-1]) || !isValidNumber(values[i+1])) break

			val num1 = values[i-1].toDouble()
			val num2 = values[i+1].toDouble()

			// todo recursive until expression is just a number
			return when (operation) {
				Operation.MULTIPLY.symbol -> multiply(num1, num2)
				Operation.DIVIDE.symbol -> divide(num1, num2)
				Operation.ADD.symbol -> add(num1, num2)
				Operation.SUBTRACT.symbol -> subtract(num1, num2)
				else -> expression
			}.toString().truncateTrailingPointZero()
		}
		return ""
	}

	private fun percentage(expression: String): String {
		// TODO figure out how to resolve floating point inaccuracy
		val result = calculateOrderOfOperations(expression).toDouble() / 100
		return formatCalculation(result.toString())
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
		// todo figure out floating point inaccuracy
		return dividend / divisor
	}
}