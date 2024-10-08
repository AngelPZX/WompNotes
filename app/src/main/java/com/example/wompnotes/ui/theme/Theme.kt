package com.example.wompnotes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Definir los colores para el modo claro
private val LightColorPalette = lightColorScheme(
    primary = Color(0xFFFFD700),  // Amarillo dorado
    onPrimary = Color(0xFF5D4037),  // Café oscuro
    primaryContainer = Color(0xFFFFF8E1), // Fondo amarillo claro
    secondary = Color(0xFF795548),  // Café medio
    onSecondary = Color.White,
    background = Color(0xFFFFFDE7), // Fondo de aplicación amarillo suave
    surface = Color(0xFFFFF8E1),  // Superficies (cartas, botones)
    onBackground = Color(0xFF5D4037), // Texto principal
    onSurface = Color(0xFF5D4037),  // Texto en superficies
)

// Definir los colores para el modo oscuro
private val DarkColorPalette = darkColorScheme(
    primary = Color(0xFFFFD54F),  // Amarillo más fuerte
    onPrimary = Color(0xFF3E2723),  // Café muy oscuro
    primaryContainer = Color(0xFF6D4C41),  // Fondo oscuro
    secondary = Color(0xFF8D6E63),  // Café más suave
    onSecondary = Color.Black,
    background = Color(0xFF3E2723),  // Fondo general
    surface = Color(0xFF5D4037),  // Superficies en modo oscuro
    onBackground = Color(0xFFFFD54F),  // Texto de fondo
    onSurface = Color(0xFFFFD54F),  // Texto en superficie
)

@Composable
fun WompNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography, // Tipografía personalizada
        shapes = AppShapes,      // Ajuste: Usar `AppShapes` en lugar de `Shapes`
        content = content
    )
}
