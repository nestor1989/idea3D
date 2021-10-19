package com.idea3d.idea3d.ui.view.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.idea3d.idea3d.ui.theme.Blue800
import com.idea3d.idea3d.ui.theme.CyanA400
import com.idea3d.idea3d.ui.theme.CyanA700
import com.idea3d.idea3d.ui.theme.Purple500

private val DarkColorPalette = darkColors(
    primary = Blue800,
    primaryVariant = CyanA700,
    secondary = CyanA700
)

private val LightColorPalette = lightColors(
    primary = Blue800,
    primaryVariant = CyanA700,
    secondary = CyanA400

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun Idea3DTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}