package com.example.appproductos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

// Colores del tema claro
private val LightColors = lightColorScheme(
    primary = onBackgroundLight, //Texto Oscuro Titulo
    onPrimary = onPrimaryLight, //Letras blancas
    primaryContainer = primaryContainerLightMediumContrast, //Contenedor Azul Marino
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = inverseSurfaceLightHighContrast, //Texto Gris Oscuro Subtitulo
    onSecondary = onSecondaryLight,
    secondaryContainer = inversePrimaryLightMediumContrast, //Contenedor Secundario Azul Claro
    onSecondaryContainer = secondaryContainerLight, //Contenedor de color Azul muy claro medio blanco
    tertiary = backgroundLight, //Titulo Blanco
    onTertiary = onTertiaryLight,
    tertiaryContainer = primaryLight,
    onTertiaryContainer = onTertiaryContainerLight, //Letras color Morado Muy Oscuro
    error = errorLight,
    errorContainer = errorContainerLight,
    onError = onErrorLight,
    onErrorContainer = onErrorContainerLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = inversePrimaryDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceLight,
    inverseSurface = primaryContainerLightMediumContrast,
    inversePrimary = secondaryContainerLight
)

// Colores del tema oscuro
private val DarkColors = darkColorScheme(
    primary = primaryDark,
    onPrimary = surfaceDark, //Letras Negras
    primaryContainer = onPrimaryDarkMediumContrast, //Contendor Azul muy oscuro
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = primaryContainerDark, //Contenedor Azul menos oscuro
    onSecondaryContainer = onPrimaryDark, //Contenedor Azul un poco menos oscuro
    tertiary = onBackgroundDarkMediumContrast, //Titulo Blanco
    onTertiary = onTertiaryDark,
    tertiaryContainer = primaryContainerDarkMediumContrast, //Azul claro
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorContainerDark,
    errorContainer = errorContainerDark,
    onError = onErrorDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inverseSurface = primaryDarkMediumContrast, // azul clarito
    inversePrimary = onPrimaryDark, // Azul un poco menos oscuro
)

@Composable
fun AppProductosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
