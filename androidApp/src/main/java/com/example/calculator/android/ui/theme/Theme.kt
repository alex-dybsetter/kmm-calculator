package com.example.calculator.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
fun numberButtonStyle(
	darkTheme: Boolean = isSystemInDarkTheme()
): ButtonColors {
	return ButtonDefaults.buttonColors(
		backgroundColor = if (darkTheme) primaryDark else primaryLight
	)
}

@Composable
fun operationButtonStyle(
	darkTheme: Boolean = isSystemInDarkTheme()
): ButtonColors {
	return ButtonDefaults.buttonColors(
		backgroundColor = if (darkTheme) accentDark else accentLight
	)
}

@Composable
fun expressionModifierButtonStyle(
	darkTheme: Boolean = isSystemInDarkTheme()
): ButtonColors {
	return ButtonDefaults.buttonColors(
		backgroundColor = if (darkTheme) primaryVariantDark else primaryVariantLight
	)
}