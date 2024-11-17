package com.tifd.prakpapb.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Typography
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button

private val DarkColorScheme = darkColorScheme(
    primary = NeonBlue,
    secondary = GrayBlue,
    tertiary = DarkBlue
)

private val LightColorScheme = lightColorScheme(
    primary = NeonBlue,
    secondary = GrayBlue,
    tertiary = DarkBlue

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PrakPAPBTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Define colors for buttons
    val customButtonColors = ButtonDefaults.buttonColors(
        containerColor = DarkBlue, // Set background color for button
        contentColor = AlmostWhite // Set text color for button
    )

    // Define button shape
    val customButtonShape = MaterialTheme.shapes.medium // Use medium shape from theme

    // MaterialTheme with the defined button styles
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        shapes = Shapes(
            small = customButtonShape, // Apply custom button shape here
            medium = customButtonShape,
            large = customButtonShape
        ),
        content = content
    )
}