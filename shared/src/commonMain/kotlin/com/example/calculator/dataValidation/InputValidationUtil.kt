package com.example.calculator.dataValidation

import com.example.calculator.DECIMAL_BUTTON
import com.example.calculator.extensions.operationsRegex
import com.example.calculator.extensions.removeLeadingZeros
import com.example.calculator.extensions.removeTrailingDecimals
import com.example.calculator.extensions.replaceLast
import com.example.calculator.PERCENT_BUTTON

fun getUpdatedExpression(
	expression: String,
	currentValue: String,
	newInput: String
): String {
	 val updatedExpression =  when {
		 newInput == PERCENT_BUTTON ->
			 validatePercentage(expression.removeTrailingDecimals(), currentValue, newInput)
		 newInput == DECIMAL_BUTTON ->
			 validateDecimal(expression)
		 isDigit(newInput) ->
			 validateNumber(expression.removeLeadingZeros(), newInput)
		 isOperation(newInput) ->
			 validateOperation(expression.removeTrailingDecimals(), currentValue, newInput)
		else -> expression + newInput
	}
	return updatedExpression
}

private fun validatePercentage(
	expression: String,
	currentValue: String,
	newInput: String
): String {
	val lastEntry = expression.last().toString()
	return when {
		// If performing a percentage operation on an expression that was already converted to percent
		// Then use the current value of the previous calculation for the next calculation
		lastEntry == PERCENT_BUTTON -> currentValue + newInput
		isOperation(lastEntry) -> expression.replaceLast(newInput)
		else -> expression + newInput
	}
}

private fun validateNumber(
	expression: String,
	newInput: String
): String {
	return when {
		// If appending a digit to an operation that computed the percentage of a value
		// Then clear the previous entry and return the new input
		expression.contains(PERCENT_BUTTON) -> newInput

		else -> expression + newInput
	}
}

private fun validateDecimal(expression: String): String {
	// Correct notation has a zero before a decimal
	if (expression.isEmpty()) return "0$DECIMAL_BUTTON"

	val values = expression.split(operationsRegex)

	return when {
		// Correct notation has a zero before a decimal
		values.last().isEmpty() -> expression + "0$DECIMAL_BUTTON"

		// Decimals can only be added once in a single number
		values.last().contains(DECIMAL_BUTTON) -> expression

		// Append the decimal
		else -> "$expression$DECIMAL_BUTTON"
	}
}

private fun validateOperation(
	expression: String,
	currentValue: String,
	newInput: String
): String {
	val lastEntry = expression.last().toString()

	return when {
		lastEntry == PERCENT_BUTTON -> currentValue + newInput
		isOperation(lastEntry) -> expression.replaceLast(newInput)
		else -> expression + newInput
	}
}

private fun isDigit(entry: String) = entry.matches("\\d".toRegex())
private fun isOperation(entry: String) = entry.matches(operationsRegex)