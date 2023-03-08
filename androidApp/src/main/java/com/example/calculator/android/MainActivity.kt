package com.example.calculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.calculator.Calculator
import com.example.calculator.android.ui.screens.BasicCalculator
import com.example.calculator.android.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CalculatorAppTheme { App(Calculator()) }
		}
	}
}

@Composable
fun App(calculator: Calculator) {
	BasicCalculator(calculator)
}