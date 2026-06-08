package com.adopcion.presentation.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adopcion.theme.*

@Composable
fun AdminDashboardScreen(
    onMascotasClick: () -> Unit,
    onFundacionesClick: () -> Unit,
    onRescatesClick: () -> Unit,
    onDonacionesClick: () -> Unit,
    onSolicitudesClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        AdminHeader()

        Text(
            text = "Gestión principal",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )

        AdminCard(
            emoji = "🐶",
            title = "Mascotas",
            subtitle = "Agregar, revisar y eliminar mascotas en adopción",
            buttonText = "Gestionar mascotas",
            onClick = onMascotasClick
        )

        AdminCard(
            emoji = "🏡",
            title = "Fundaciones",
            subtitle = "Registra refugios y centros aliados",
            buttonText = "Gestionar fundaciones",
            onClick = onFundacionesClick
        )

        AdminCard(
            emoji = "🚑",
            title = "Rescates",
            subtitle = "Controla casos de animales rescatados",
            buttonText = "Gestionar rescates",
            onClick = onRescatesClick
        )

        Text(
            text = "Revisión",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )

        AdminCard(
            emoji = "📋",
            title = "Solicitudes",
            subtitle = "Revisa adopciones pendientes",
            buttonText = "Ver solicitudes",
            onClick = onSolicitudesClick
        )

        AdminCard(
            emoji = "💚",
            title = "Donaciones",
            subtitle = "Consulta los apoyos recibidos",
            buttonText = "Ver donaciones",
            onClick = onDonacionesClick
        )

        OutlinedButton(
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Text("Cerrar sesión")
        }

        Text(
            text = "🐾 Cada gestión ayuda a que un perrito encuentre hogar.",
            color = TextSecondary
        )
    }
}

@Composable
private fun AdminHeader() {
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
                Text("🐾", style = MaterialTheme.typography.headlineLarge)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text = "Panel Admin",
                    style = MaterialTheme.typography.headlineLarge,
                    color = TextPrimary
                )
                Text(
                    text = "Gestiona adopciones, rescates y fundaciones",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun AdminCard(
    emoji: String,
    title: String,
    subtitle: String,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Surface2, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(emoji)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Accent,
                    contentColor = AccentOnDark
                )
            ) {
                Text(buttonText)
            }
        }
    }
}