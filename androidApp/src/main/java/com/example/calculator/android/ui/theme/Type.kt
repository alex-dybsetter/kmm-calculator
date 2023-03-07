package com.example.calculator.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val Typography = Typography(
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	button = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.SemiBold,
		fontSize = 30.sp
	)
)

val inputTextStyle: TextStyle = TextStyle(
	textAlign = TextAlign.End,
	fontSize = 50.sp
)

val outputTextStyle: TextStyle = TextStyle(
	textAlign = TextAlign.End,
	fontSize = 30.sp
)