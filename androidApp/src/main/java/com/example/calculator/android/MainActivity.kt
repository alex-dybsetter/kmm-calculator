package com.example.calculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Calculator
import com.example.calculator.InputValidationHelper

@Composable
fun MyApplicationTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colors = if (darkTheme) {
		darkColors(
			primary = Color(0xFF546e7a),
			primaryVariant = Color(0xFF29434e),
			secondary = Color(0xFFaeea00),
			background = Color(0xFF37474f),
			onPrimary = Color.White,
			onSecondary = Color.Black,
			onBackground = Color.White,
		)
	} else {
		lightColors(
			primary = Color(0xFF819ca9),
			primaryVariant = Color(0xFF546e7a),
			secondary = Color(0xFFaeea00),
			background = Color(0xFFeceff1),
			onPrimary = Color.Black,
			onSecondary = Color.Black,
			onBackground = Color.Black,
		)
	}
	val typography = Typography(
		body1 = TextStyle(
			fontFamily = FontFamily.Default,
			fontWeight = FontWeight.Normal,
			fontSize = 16.sp
		)
	)
	val shapes = Shapes(
		small = RoundedCornerShape(4.dp),
		medium = RoundedCornerShape(4.dp),
		large = RoundedCornerShape(0.dp)
	)

	MaterialTheme(
		colors = colors,
		typography = typography,
		shapes = shapes,
		content = content
	)
}

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyApplicationTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colors.background
				) {
					val calculator = Calculator()
					InputButtons(calculator)
				}
			}
		}
	}
}

@Composable
fun InputButtons(calculator: Calculator) {
	var output by remember { mutableStateOf("") }
	val updateOutput = { entry: String -> output = InputValidationHelper.validateInput(output, entry) }

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,

		) {
		TextField(
			value = output,
			onValueChange = { output = it },
			readOnly = true,
			modifier = Modifier.fillMaxWidth()
		)

		Row(
			modifier = Modifier.padding(16.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
		) {
			Column(
				modifier = Modifier.weight(1f, true)
			) {
				NumberRow {
					InputButton(onClick = updateOutput, number = "7")
					InputButton(onClick = updateOutput, number = "8")
					InputButton(onClick = updateOutput, number = "9")
				}
				NumberRow {
					InputButton(onClick = updateOutput, number = "4")
					InputButton(onClick = updateOutput, number = "5")
					InputButton(onClick = updateOutput, number = "6")
				}
				NumberRow {
					InputButton(onClick = updateOutput, number = "1")
					InputButton(onClick = updateOutput, number = "2")
					InputButton(onClick = updateOutput, number = "3")
				}
				NumberRow {
					InputButton(onClick = updateOutput, number = ".")
					InputButton(onClick = updateOutput, number = "0")
					InputButton(onClick = updateOutput, number = "0")
				}
			}
			Column {
				OperationButton(operation = Calculator.Operation.ADD, updateOutput)
				OperationButton(operation = Calculator.Operation.SUBTRACT, updateOutput)
				OperationButton(operation = Calculator.Operation.MULTIPLY, updateOutput)
				OperationButton(operation = Calculator.Operation.DIVIDE, updateOutput)
				Button(
					onClick = { output = calculator.calculate(output) },
					colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFaeea00))
				) {
					Text(text = "=")
				}
			}
		}
	}
}

@Composable
fun NumberRow(content: @Composable RowScope.() -> Unit) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
		modifier = Modifier.fillMaxWidth()
	) {
		content()
	}
}

@Composable
fun RowScope.InputButton(number: String, onClick: (String) -> Unit) {
	Button(
		onClick = { onClick.invoke(number) },
		modifier = Modifier.weight(1f, true)
	) {
		Text(text = number)
	}
}

@Composable
fun OperationButton(operation: Calculator.Operation, onClick: (String) -> Unit) {
	Button(
		onClick = { onClick.invoke(operation.symbol) },
		colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFaeea00))
	) {
		Text(text = operation.symbol)
	}
}