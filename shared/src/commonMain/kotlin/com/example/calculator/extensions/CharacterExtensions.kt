package com.example.calculator.extensions

import com.example.calculator.Calculator

fun Char.isAdditionOperation() =
	Calculator.Operation.ADD.symbol == this.toString()

fun Char.isSubtractionOperation() =
	Calculator.Operation.SUBTRACT.symbol == this.toString()

fun Char.isDivisionOperation() =
	Calculator.Operation.DIVIDE.symbol == this.toString()

fun Char.isMultiplicationOperation() =
	Calculator.Operation.MULTIPLY.symbol == this.toString()