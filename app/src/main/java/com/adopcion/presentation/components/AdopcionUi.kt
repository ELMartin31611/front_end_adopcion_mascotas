package com.adopcion.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adopcion.theme.*

@Composable
fun PawHeader(
    title: String,
    subtitle: String,
    emoji: String = "🐶"
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = Surface2),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(AccentLight, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = emoji, style = MaterialTheme.typography.headlineLarge)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(title, style = MaterialTheme.typography.headlineLarge, color = TextPrimary)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = TextPrimary,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
}

@Composable
fun PrettyCard(
    title: String,
    subtitle: String,
    emoji: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Surface2, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = emoji)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(title, style = MaterialTheme.typography.titleLarge, color = TextPrimary)
                    Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun PrettyButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
            contentColor = AccentOnDark
        )
    ) {
        Text(text)
    }
}

@Composable
fun PrettyOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = AccentDark
        )
    ) {
        Text(text)
    }
}

@Composable
fun InfoChip(
    text: String,
    emoji: String = "🌿"
) {
    Surface(
        color = Surface2,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(
            text = "$emoji $text",
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = TextSecondary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun StatusChip(
    text: String
) {
    val color = when (text.lowercase()) {
        "aprobado", "aprobada", "disponible", "activo" -> StatusApproved
        "pendiente", "en proceso" -> StatusPending
        "rechazado", "rechazada", "eliminado" -> StatusRejected
        else -> Accent
    }

    Surface(
        color = color.copy(alpha = 0.15f),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Text(
            text = text,
            color = color,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun EmptyState(
    emoji: String = "🐾",
    title: String = "Nada por aquí",
    message: String = "Todavía no hay información registrada."
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Surface2)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emoji, style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.titleLarge, color = TextPrimary)
            Text(message, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
        }
    }
}

@Composable
fun LoadingState(
    message: String = "Cargando información..."
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Accent)
        Spacer(modifier = Modifier.height(12.dp))
        Text(message, color = TextSecondary)
    }
}

@Composable
fun ErrorState(
    message: String,
    onRetry: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Error.copy(alpha = 0.10f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Ups, algo salió mal 🐶", style = MaterialTheme.typography.titleLarge, color = Error)
            Text(message, color = TextSecondary)

            if (onRetry != null) {
                Spacer(modifier = Modifier.height(10.dp))
                PrettyOutlinedButton("Reintentar", onRetry)
            }
        }
    }
}

@Composable
fun AdminStatCard(
    title: String,
    value: String,
    emoji: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(value, style = MaterialTheme.typography.headlineLarge, color = AccentDark)
                Text(title, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
            }
        }
    }
}