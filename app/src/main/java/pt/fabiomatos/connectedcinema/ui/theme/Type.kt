package pt.fabiomatos.connectedcinema.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pt.fabiomatos.connectedcinema.R

private val myCustomFont = FontFamily(
    Font(R.font.robotocondensed_regular),
)

val RobotoCondensed = FontFamily(
    Font(R.font.robotocondensed_regular, FontWeight.Normal),
    Font(R.font.robotocondensed_light,FontWeight.Light),
    Font(R.font.robotocondensed_bold, FontWeight.Bold),
    Font(R.font.robotocondensed_thin, FontWeight.Thin)
)

// Set of Material typography styles to start with
val Typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = RobotoCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge= TextStyle(fontFamily = RobotoCondensed),
    displayMedium= TextStyle(fontFamily = RobotoCondensed),
    displaySmall= TextStyle(fontFamily = RobotoCondensed),
    headlineLarge= TextStyle(fontFamily = RobotoCondensed),
    headlineMedium= TextStyle(fontFamily = RobotoCondensed),
    headlineSmall= TextStyle(fontFamily = RobotoCondensed),
    titleLarge= TextStyle(fontFamily = RobotoCondensed),
    titleMedium= TextStyle(fontFamily = RobotoCondensed),
    titleSmall= TextStyle(fontFamily = RobotoCondensed),
    bodyMedium= TextStyle(fontFamily = RobotoCondensed),
    bodySmall= TextStyle(fontFamily = RobotoCondensed),
    labelLarge= TextStyle(fontFamily = RobotoCondensed),
    labelMedium= TextStyle(fontFamily = RobotoCondensed),
    labelSmall= TextStyle(fontFamily = RobotoCondensed),

)