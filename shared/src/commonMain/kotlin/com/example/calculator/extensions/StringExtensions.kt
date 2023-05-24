package com.example.calculator.extensions

import com.example.calculator.Calculator

val operationsRegex =
	"[${Calculator.Operation.SUBTRACT.symbol}${Calculator.Operation.ADD.symbol}${Calculator.Operation.MULTIPLY.symbol}${Calculator.Operation.DIVIDE.symbol}]".toRegex()

fun String.deleteLast(): String {
	return if (length > 1) this.substring(0, this.lastIndex) else "0"
}

fun String.replaceLast(newChar: String): String {
	return this.deleteLast() + newChar
}

fun String.removeLeadingZeros(): String {
	val numbers = this.split(operationsRegex)
	return if (numbers.last() == "0") {
		// Last value in the expression started with 0, remove it and return the expression
		this.removeSuffix("0")
	} else this
}

fun String.removeTrailingDecimals(): String {
	return if (this.last() == '.') deleteLast() else this.truncateTrailingPointZero()
}

fun String.truncateTrailingPointZero(): String {
	return if (this.endsWith(".0")) {
		// Last value in the expression started with .0, remove it and return the expression
		this.removeSuffix(".0")
	} else this
}