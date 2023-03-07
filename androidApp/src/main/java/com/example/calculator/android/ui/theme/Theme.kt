package com.example.calculator.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkColorPalette = darkColors(
	primary = primaryDark,
	primaryVariant = primaryVariantDark,
	secondary = accentDark,
	background = Color.Black,
	onSurface = fontDark,
	onPrimary = fontDark,
	surface = grayDark,
)

private val LightColorPalette = lightColors(
	primary = primaryLight,
	primaryVariant = primaryVariantLight,
	secondary = accentLight,
	background = Color.White,
	onSurface = fontLight,
	onPrimary = fontLight,
	surface = grayLight

	/* Other default colors to override
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

val outputModifierButtonStyle = null

@Composable
fun CalculatorAppTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val colors =
		if (darkTheme) DarkColorPalette
		else LightColorPalette

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}

@Composable
fun NumberButtonStyle(content: @Composable () -> Unit) {
	MaterialTheme { content() }
}

@Composable
fun OperationButtonStyle(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	MaterialTheme(
		colors = MaterialTheme.colors.copy(
			primary = if (darkTheme) accentDark else accentLight
		)
	) {
		content()
	}
}

@Composable
fun OutputModifierButtonStyle(
	darkTheme: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	MaterialTheme(
		colors = MaterialTheme.colors.copy(
			primary = if (darkTheme) primaryVariantDark else primaryVariantLight
		)
	) {
		content()
	}
}