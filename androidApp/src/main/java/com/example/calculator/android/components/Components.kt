package com.example.calculator.android.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun VerticallyCenteredText(
	modifier: Modifier = Modifier,
	text: String,
	textStyle: TextStyle = TextStyle()
) {
	Box(
		modifier = modifier.padding(16.dp),
		contentAlignment = Alignment.Center,
	) {
		Text(
			text = text,
			modifier = modifier.fillMaxWidth(),
			style = textStyle
		)
	}
}

@Composable
fun OutlinedButton(
	modifier: Modifier = Modifier,
	text: String,
	colorScheme: ButtonColors = ButtonDefaults.buttonColors(),
	onClick: () -> Unit
) {
	Button(
		onClick = { onClick() },
		modifier = modifier
			.fillMaxSize()
			.border(width = 1.dp, color = Color.White),
		colors = colorScheme
	) {
		Text(text = text)
	}
}