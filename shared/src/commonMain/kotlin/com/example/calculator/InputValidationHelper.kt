package com.example.calculator

import com.example.calculator.Calculator.Operation.*

object InputValidationHelper {

	private val operationsRegex =
		"[${SUBTRACT.symbol}${ADD.symbol}${MULTIPLY.symbol}${DIVIDE.symbol}]".toRegex()

	fun validateInput(expression: String, entry: String): String {
		return when {
			isDigit(entry) -> removeLeadingZeros(expression, entry)

			isOperation(entry) && !isOperation(expression.last().toString()) ->
				removeTrailingDecimals(expression, entry)

			isDecimal(entry) -> getValidatedDecimalString(expression)

			else -> expression
		}
	}

	private fun removeLeadingZeros(expression: String, entry: String): String {
		// Empty expression, append the entry
		if (expression.isEmpty()) return expression + entry

		val numbers = expression.split(operationsRegex)

		return when {
			// Last number in expression is empty, append the entry
			numbers.last().isEmpty() -> expression + entry

			// Last number in the expression started with 0, replace with the entry
			numbers.last() == "0" -> expression.removeSuffix("0") + entry

			// Append the entry
			else -> expression + entry
		}
	}

	private fun removeTrailingDecimals(expression: String, entry: String): String {
		return expression.removeSuffix(".") + entry
	}

	private fun getValidatedDecimalString(expression: String): String {
		// Correct notation has a zero before a decimal
		if (expression.isEmpty()) return "0."

		val numbers = expression.split(operationsRegex)

		return when {
			// Correct notation has a zero before a decimal
			numbers.last().isEmpty() -> expression + "0."

			// Decimals can only be added once in a single number
			numbers.last().contains(".") -> expression

			// Append the decimal
			else -> "$expression."
		}
	}

	private fun isDigit(entry: String) = entry.matches("[0-9]".toRegex())

	private fun isOperation(entry: String) = entry.matches(operationsRegex)

	private fun isDecimal(entry: String) = entry == "."
}