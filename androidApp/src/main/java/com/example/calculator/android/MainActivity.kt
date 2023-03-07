package com.example.calculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.calculator.Calculator
import com.example.calculator.android.components.OutlinedButton
import com.example.calculator.android.components.VerticallyCenteredText
import com.example.calculator.android.ui.theme.*

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CalculatorAppTheme { App(Calculator()) }
		}
	}
}

@Composable
private fun App(calculator: Calculator) {
	var expression by remember { mutableStateOf("") }
	var calculation by remember { mutableStateOf("") }
	val operations = calculator.getOperations()

	Surface {
		Column {
			VerticallyCenteredText(
				modifier = Modifier.weight(1.2f, true),
				text = expression,
				textStyle = inputTextStyle
			)

			VerticallyCenteredText(
				modifier = Modifier
					.weight(.8f, true)
					.alpha(.75f),
				text = calculation,
				textStyle = outputTextStyle
			)

			val equalDistributionModifier = Modifier.weight(1f, true)
			Row(modifier = equalDistributionModifier) {
				OutlinedButton(
					modifier = equalDistributionModifier,
					text = "AC",
					colorScheme = expressionModifierButtonStyle()
				) {
					expression = ""
					calculation = ""
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = "+/-",
					colorScheme = expressionModifierButtonStyle()
				) {
					expression = calculator.numberSignChanged(expression)
					calculation = calculator.calculate(expression)
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = "%",
					colorScheme = expressionModifierButtonStyle()
				) {
					expression += "%"
					calculation = calculator.percentage(expression)
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = operations[0],
					colorScheme = operationButtonStyle()
				) {
					expression = calculator.onOperationClicked(operations[0])
					calculation = ""
				}
			}
			NumberPad(
				operations = operations,
				modifier = equalDistributionModifier
			)
			Row(modifier = equalDistributionModifier) {
				OutlinedButton(
					modifier = Modifier.weight(2f, true),
					text = "0",
					colorScheme = numberButtonStyle()
				) {
					// todo
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = ".",
					colorScheme = numberButtonStyle()
				) {
					// todo
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = "=",
					colorScheme = operationButtonStyle()
				) {
					// todo
				}
			}
		}
	}
}

@Composable
private fun NumberPad(
	operations: Array<String>,
	modifier: Modifier = Modifier
) {
	var i = 1
	for (x in 7 downTo 1 step 3) {
		NumberRow(
			firstNumInRow = x,
			modifier = modifier
		) {
			OutlinedButton(
				modifier = modifier,
				text = operations[i++],
				colorScheme = operationButtonStyle()
			) {
				// todo
			}
		}
	}
}

@Composable
fun NumberRow(
	firstNumInRow: Int,
	modifier: Modifier = Modifier,
	content: @Composable RowScope.() -> Unit
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
		modifier = modifier.fillMaxWidth()
	) {
		OutlinedButton(
			modifier = modifier,
			text = "$firstNumInRow",
			colorScheme = numberButtonStyle()
		) {
			// todo
		}

		OutlinedButton(
			modifier = modifier,
			text = "${firstNumInRow + 1}",
			colorScheme = numberButtonStyle()
		) {
			// todo
		}

		OutlinedButton(
			modifier = modifier,
			text = "${firstNumInRow + 2}",
			colorScheme = numberButtonStyle()
		) {
			// todo
		}

		content()
	}
}