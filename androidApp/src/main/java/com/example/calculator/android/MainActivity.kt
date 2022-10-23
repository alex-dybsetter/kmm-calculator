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
import com.example.calculator.*

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

// TODO: Why are composable methods capitalized?
@Composable
fun InputButtons(calculator: Calculator) {
	// TODO: Are input values only available in the scope of the composable?
	var operation by remember { mutableStateOf("") }
	var output by remember { mutableStateOf("") }

	val onOperationClicked: (String) -> Unit = {
		if (output.isNotEmpty()) {
			output =
				if (output.contains(" ")) output.replace(operation, it)
				else "$output $it "

			operation = it
		}
	}

	val onEqualsClicked: (String) -> Unit = {
		if (operation.isNotEmpty() && output.last() != ' ') {
			val parts = output.split(" ")
			val num1 = parts[0].toDouble()
			val num2 = parts[2].toDouble()
			val operator = parts[1]

			val result = calculator.equals(num1, num2, operator)

			output = result.toString()
			operation = ""
		}
	}

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
				NumberPad { number ->
					output += number
				}

				ClearButton {
					output = ""
					operation = ""
				}
			}
			Column {
				OperationPad(onOperationClicked)
				OperationButton(onEqualsClicked, OPERATION_CALCULATE)
			}
		}
	}
}

@Composable
fun NumberPad(onDigitClicked: (Int) -> Unit) {
	NumberRow {
		NumberButton(onClick = onDigitClicked, number = 7)
		NumberButton(onClick = onDigitClicked, number = 8)
		NumberButton(onClick = onDigitClicked, number = 9)
	}
	NumberRow {
		NumberButton(onClick = onDigitClicked, number = 4)
		NumberButton(onClick = onDigitClicked, number = 5)
		NumberButton(onClick = onDigitClicked, number = 6)
	}
	NumberRow {
		NumberButton(onClick = onDigitClicked, number = 1)
		NumberButton(onClick = onDigitClicked, number = 2)
		NumberButton(onClick = onDigitClicked, number = 3)
	}
	NumberRow {
		NumberButton(onClick = onDigitClicked, number = 0)
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
fun NumberButton(onClick: (Int) -> Unit, number: Int) {
	// TODO: How do you style the buttons?
	Button(
		onClick = { onClick.invoke(number) },
		colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFaeea00))
	) {
		Text(text = number.toString())
	}
}

@Composable
fun OperationPad(onOperationClicked: (String) -> Unit) {
	OperationButton(onClick = onOperationClicked, operation = OPERATION_ADD)
	OperationButton(onClick = onOperationClicked, operation = OPERATION_SUBTRACT)
	OperationButton(onClick = onOperationClicked, operation = OPERATION_MULTIPLY)
	OperationButton(onClick = onOperationClicked, operation = OPERATION_DIVIDE)
}

@Composable
fun OperationButton(onClick: (String) -> Unit, operation: String) {
	Button(
		onClick = { onClick.invoke(operation) }
	) {
		Text(text = operation)
	}
}


@Composable
fun ClearButton(onClick: () -> Unit) {
	Button(
		onClick = onClick,
		modifier = Modifier.fillMaxWidth(),
		colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
	) {
		Text(
			text = "CLEAR ALL",
			color = Color.Black
		)
	}
}

