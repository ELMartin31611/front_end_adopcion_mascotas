package com.adopcion.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adopcion.theme.*

@Composable
fun ShopButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier.fillMaxWidth().height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
            contentColor = AccentOnDark,
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(if (isLoading) "Cargando..." else text)
    }
}

@Composable
fun ShopOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth().height(52.dp),
    ) {
        Text(text)
    }
}