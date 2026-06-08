package com.adopcion.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(

    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),

    headlineLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),

    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),

    bodyLarge = TextStyle(
        fontSize = 16.sp
    ),

    bodyMedium = TextStyle(
        fontSize = 14.sp
    ),

    labelLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    )
)