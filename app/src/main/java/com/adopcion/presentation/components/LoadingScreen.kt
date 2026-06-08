package com.adopcion.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adopcion.theme.*

@Composable
fun LoadingScreen(message: String = "Cargando...") {
    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = Accent)
            Spacer(Modifier.height(16.dp))
            Text(message, color = TextSecondary)
        }
    }
}

@Composable
fun ErrorScreen(message: String, onRetry: (() -> Unit)? = null) {
    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Error")
            Spacer(Modifier.height(8.dp))
            Text(message)
            if (onRetry != null) {
                Spacer(Modifier.height(16.dp))
                ShopButton("Reintentar", onRetry)
            }
        }
    }
}