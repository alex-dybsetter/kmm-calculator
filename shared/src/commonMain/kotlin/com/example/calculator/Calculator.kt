package com.example.calculator

const val OPERATION_ADD = "+"
const val OPERATION_SUBTRACT = "-"
const val OPERATION_MULTIPLY = "x"
const val OPERATION_DIVIDE = "/"
const val OPERATION_CALCULATE = "="

class Calculator {

	fun equals(num1: Double, num2: Double, operationType: String): Double {
		if (num1 == 0.0 && num2 == 0.0) return 0.0

		if (operationType.isNotEmpty()) {
			return when (operationType) {
				OPERATION_ADD -> add(num1, num2)
				OPERATION_SUBTRACT -> subtract(num1, num2)
				OPERATION_MULTIPLY -> multiply(num1, num2)
				OPERATION_DIVIDE -> divide(num1, num2)
				else -> throw IllegalArgumentException("Unknown operation type")
			}
		}

		throw IllegalArgumentException("Operation type must be specified")
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