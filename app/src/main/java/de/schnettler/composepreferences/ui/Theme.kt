package de.schnettler.composepreferences.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkThemeColorScheme = darkColorScheme()

private val LightThemeColorScheme = lightColorScheme()

@Composable
fun ComposePreferencesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> DarkThemeColorScheme
        else -> LightThemeColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}