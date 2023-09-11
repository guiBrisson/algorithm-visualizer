package me.brisson.algorithm_visualizer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.brisson.algorithm_visualizer.R

val interFontFamily = FontFamily(
    Font(R.font.inter_thin, weight = FontWeight.Thin),
    Font(R.font.inter_light, weight = FontWeight.Light),
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_medium, weight = FontWeight.Medium),
    Font(R.font.inter_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
)

val jetbrainsMonoFontFamily = FontFamily(
    Font(R.font.jetbrains_mono_thin, weight = FontWeight.Thin),
    Font(R.font.jetbrains_mono_light, weight = FontWeight.Light),
    Font(R.font.jetbrains_mono_regular, weight = FontWeight.Normal),
    Font(R.font.jetbrains_mono_medium, weight = FontWeight.Medium),
    Font(R.font.jetbrains_mono_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.jetbrains_mono_bold, weight = FontWeight.Bold),
)

val display2Style = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 25.sp,
    fontWeight = FontWeight.Normal
)

val display1Style = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 22.sp,
    fontWeight = FontWeight.Normal
)

val headlineStyle = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 20.sp,
    fontWeight = FontWeight.Medium
)

val titleStyle = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 18.sp,
    fontWeight = FontWeight.Medium
)

val subheadStyle = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal
)

val body1Style = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal
)

val captionStyle = TextStyle(
    fontFamily = interFontFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal
)

val consoleStyle = TextStyle(
    fontFamily = jetbrainsMonoFontFamily,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal
)

val Typography = Typography(
    labelSmall = captionStyle,
    bodyMedium = body1Style,
    titleSmall = subheadStyle,
    titleMedium = titleStyle,
    headlineMedium = headlineStyle,
    displaySmall = display1Style,
    displayMedium = display2Style,
)