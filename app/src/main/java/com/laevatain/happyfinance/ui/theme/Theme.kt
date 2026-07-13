package com.laevatain.happyfinance.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

data class AppColors(
    val bg: androidx.compose.ui.graphics.Color,
    val surface: androidx.compose.ui.graphics.Color,
    val border: androidx.compose.ui.graphics.Color,
    val textPrimary: androidx.compose.ui.graphics.Color,
    val textSecondary: androidx.compose.ui.graphics.Color,
    val isDark: Boolean
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        bg = DarkBg,
        surface = DarkSurface,
        border = DarkBorder,
        textPrimary = DarkTextPrimary,
        textSecondary = DarkTextSecondary,
        isDark = true
    )
}

private val DarkColorScheme = darkColorScheme(
    primary = Brand,
    secondary = Portfolio,
    error = Danger,
    background = DarkBg,
    surface = DarkSurface,
)

private val LightColorScheme = lightColorScheme(
    primary = Brand,
    secondary = Portfolio,
    error = Danger,
    background = LightBg,
    surface = LightSurface,
)

@Composable
fun HappyFinanceTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val appColors = if (darkTheme) {
        AppColors(
            bg = DarkBg,
            surface = DarkSurface,
            border = DarkBorder,
            textPrimary = DarkTextPrimary,
            textSecondary = DarkTextSecondary,
            isDark = true
        )
    } else {
        AppColors(
            bg = LightBg,
            surface = LightSurface,
            border = LightBorder,
            textPrimary = LightTextPrimary,
            textSecondary = LightTextSecondary,
            isDark = false
        )
    }

    CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
            typography = Typography,
            content = content
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}