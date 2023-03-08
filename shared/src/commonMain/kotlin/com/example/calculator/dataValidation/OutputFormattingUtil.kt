package com.example.calculator.dataValidation

import com.example.calculator.extensions.removeTrailingDecimals

fun formatCalculation(result: String): String {
	return result.removeTrailingDecimals()
}