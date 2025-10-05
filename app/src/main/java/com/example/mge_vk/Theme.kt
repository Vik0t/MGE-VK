package com.example.mge_vk

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val VKDarkColorScheme = darkColorScheme(
    primary = Color(0xFF5C9CE6), // Насыщенный светло-синий (главный цвет)
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF3A6DB0),
    onPrimaryContainer = Color(0xFFFFFFFF),

    secondary = Color(0xFF7BB1FF), // Яркий голубой (вторичный)
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF4A7BC4),
    onSecondaryContainer = Color(0xFFFFFFFF),

    tertiary = Color(0xFF8BC34A), // Акцентный цвет
    onTertiary = Color(0xFF000000),

    background = Color(0xFF1E2A3A), // Темно-синий фон
    onBackground = Color(0xFFE1E5EB),

    surface = Color(0xFF253140), // Поверхности чуть светлее фона
    onSurface = Color(0xFFE1E5EB),

    surfaceVariant = Color(0xFF2D3B4D), // Вариант поверхности
    onSurfaceVariant = Color(0xFFC8D0D9),

    outline = Color(0xFF3D4A5C),
    outlineVariant = Color(0xFF4A5768),

    error = Color(0xFFCF6679),
    onError = Color(0xFF000000)
)

@Composable
fun MGEVKTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VKDarkColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}