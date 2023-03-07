package com.example.calculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculator.Calculator
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
	val operations = getOperations()

	Surface {
		Column {
			val equalDistributionModifier = Modifier.weight(1f, true)
			val buttonModifier = Modifier
				.fillMaxHeight()
				.weight(1f, true)
				.border(width = 1.dp, color = Color.White)

			OutputField(
				expression = expression,
				modifier = equalDistributionModifier
			)
			Row(modifier = equalDistributionModifier) {

				OutputModifierButton(
					modifier = buttonModifier,
					label = "AC"
				) { expression = "" }

				OutputModifierButton(
					modifier = buttonModifier,
					label = "+/-"
				) { expression = calculator.numberSignChanged(expression) }

				OutputModifierButton(
					modifier = buttonModifier,
					label = "%"
				) { expression = calculator.percentage(expression) }

				OperationButtonStyle {
					Button(
						onClick = { /*TODO*/ },
						modifier = buttonModifier
					) {
						Text(text = operations[0])
					}
				}
			}
			NumberPad(
				operations = operations,
				modifier = equalDistributionModifier
			)
			Row(modifier = equalDistributionModifier) {
				NumberButtonStyle {
					Button(
						onClick = { },
						modifier = Modifier
							.fillMaxHeight()
							.weight(2f, true)
							.border(width = 1.dp, color = Color.White)
					) {
						Text(text = "0")
					}

					Button(
						onClick = { },
						modifier = buttonModifier
					) {
						Text(text = ".")
					}
				}

				OperationButtonStyle {
					Button(
						onClick = { /*TODO*/ },
						modifier = buttonModifier
					) {
						Text("=")
					}
				}
			}
		}
	}
}

@Composable
private fun OutputField(
	expression: String,
	modifier: Modifier = Modifier
) {
	TextField(
		value = expression,
		onValueChange = {},
		modifier = modifier
			.fillMaxWidth()
			.border(width = 2.dp, color = Color.White),
		readOnly = true,
		textStyle = outputTextStyle
	)
}

@Composable
private fun OutputModifierButton(
	modifier: Modifier = Modifier,
	label: String,
	onClick: () -> Unit
) {
	OutputModifierButtonStyle {
		Button(
			onClick = { onClick() },
			modifier = modifier.fillMaxHeight()
		) {
			Text(text = label)
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
			OperationButtonStyle {
				Button(
					onClick = { /*TODO*/ },
					modifier = modifier
						.fillMaxHeight()
						.border(width = 1.dp, color = Color.White)
				) {
					Text(text = operations[i++])
				}
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
		val buttonModifier = modifier
			.fillMaxHeight()
			.border(width = 1.dp, color = Color.White)

		NumberButtonStyle {
			Button(
				onClick = { },
				modifier = buttonModifier
			) {
				Text(text = "$firstNumInRow")
			}

			Button(
				onClick = { },
				modifier = buttonModifier
			) {
				Text(text = "${firstNumInRow + 1}")
			}

			Button(
				onClick = { },
				modifier = buttonModifier
			) {
				Text(text = "${firstNumInRow + 2}")
			}
		}

		content()
	}
}

private fun getOperations(): Array<String> {
	return arrayOf(
		Calculator.Operation.DIVIDE.symbol,
		Calculator.Operation.MULTIPLY.symbol,
		Calculator.Operation.ADD.symbol,
		Calculator.Operation.SUBTRACT.symbol,
	)
}