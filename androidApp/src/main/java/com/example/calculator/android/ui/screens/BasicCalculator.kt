package com.example.calculator.android.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.calculator.*
import com.example.calculator.android.ui.components.OutlinedButton
import com.example.calculator.android.ui.components.VerticallyCenteredText
import com.example.calculator.android.ui.theme.*
import com.example.calculator.extensions.deleteLast
import com.example.calculator.dataValidation.getUpdatedExpression

@Composable
fun BasicCalculator(calculator: Calculator) {
	var expression by remember { mutableStateOf(DEFAULT_INPUT) }
	var calculation by remember { mutableStateOf(DEFAULT_OUTPUT) }
	val operations = calculator.getArithmeticOperators()

	val onInputUpdated = { input: String ->
		expression = getUpdatedExpression(expression, calculation, input)
		calculation = calculator.calculate(expression)
	}

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
					text = ALL_CANCEL_BUTTON,
					colorScheme = expressionModifierButtonStyle()
				) {
					expression = DEFAULT_INPUT
					calculation = DEFAULT_OUTPUT
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = DELETE_BUTTON,
					colorScheme = expressionModifierButtonStyle()
				) {
					expression = expression.deleteLast()
					calculation = calculator.calculate(expression)
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = PERCENT_BUTTON,
					colorScheme = expressionModifierButtonStyle()
				) {
					onInputUpdated(PERCENT_BUTTON)
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = operations[0],
					colorScheme = operationButtonStyle()
				) {
					onInputUpdated(operations[0])
				}
			}
			NumberPad(
				operations = operations,
				modifier = equalDistributionModifier,
				onNumberButtonClick = onInputUpdated,
				onOperationButtonClick = { input ->
					expression = getUpdatedExpression(expression, calculation, input)
				}
			)
			Row(modifier = equalDistributionModifier) {
				OutlinedButton(
					modifier = Modifier.weight(2f, true),
					text = "0",
					colorScheme = numberButtonStyle()
				) {
					onInputUpdated("0")
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = DECIMAL_BUTTON,
					colorScheme = numberButtonStyle()
				) {
					onInputUpdated(DECIMAL_BUTTON)
				}

				OutlinedButton(
					modifier = equalDistributionModifier,
					text = EQUALS_BUTTON,
					colorScheme = operationButtonStyle()
				) {
					calculation = calculator.calculate(expression)
				}
			}
		}
	}
}

@Composable
private fun NumberPad(
	operations: Array<String>,
	modifier: Modifier = Modifier,
	onNumberButtonClick: (String) -> Unit,
	onOperationButtonClick: (String) -> Unit
) {
	var i = 1
	for (x in 7 downTo 1 step 3) {
		NumberRow(
			firstNumInRow = x,
			modifier = modifier,
			onButtonClick = onNumberButtonClick
		) {
			val currentOperation = operations[i++]
			OutlinedButton(
				modifier = modifier,
				text = currentOperation,
				colorScheme = operationButtonStyle()
			) { onOperationButtonClick(currentOperation) }
		}
	}
}

@Composable
fun NumberRow(
	firstNumInRow: Int,
	modifier: Modifier = Modifier,
	onButtonClick: (String) -> Unit,
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
		) { onButtonClick("$firstNumInRow") }

		val secondNumInRow = "${firstNumInRow + 1}"
		OutlinedButton(
			modifier = modifier,
			text = secondNumInRow,
			colorScheme = numberButtonStyle()
		) { onButtonClick(secondNumInRow) }

		val thirdNumInRow = "${firstNumInRow + 2}"
		OutlinedButton(
			modifier = modifier,
			text = thirdNumInRow,
			colorScheme = numberButtonStyle()
		) { onButtonClick(thirdNumInRow) }

		content()
	}
}