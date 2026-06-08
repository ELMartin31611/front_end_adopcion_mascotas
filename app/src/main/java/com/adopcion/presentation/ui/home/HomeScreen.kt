package com.adopcion.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adopcion.presentation.components.*
import com.adopcion.theme.TextSecondary

@Composable
fun HomeScreen(
    onMascotasClick: () -> Unit,
    onFundacionesClick: () -> Unit,
    onSolicitudesClick: () -> Unit,
    onRescatesClick: () -> Unit,
    onDonacionesClick: () -> Unit,
    onPerfilClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {

        PawHeader(
            title = "Adopta una mascota",
            subtitle = "Dale una segunda oportunidad a un amigo peludo",
            emoji = "🐶"
        )

        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Explora mascotas, fundaciones y ayuda a los animales que más lo necesitan.",
            color = TextSecondary
        )

        SectionTitle("¿Qué deseas hacer?")

        PrettyCard(
            title = "Mascotas",
            subtitle = "Conoce perritos disponibles para adopción",
            emoji = "🐕"
        ) {
            InfoChip("Encuentra tu próximo compañero", "💚")
            Spacer(modifier = Modifier.height(10.dp))
            PrettyButton(
                text = "Ver mascotas",
                onClick = onMascotasClick
            )
        }

        PrettyCard(
            title = "Fundaciones",
            subtitle = "Refugios y organizaciones aliadas",
            emoji = "🏡"
        ) {
            InfoChip("Ayuda como voluntario", "🤝")
            Spacer(modifier = Modifier.height(10.dp))
            PrettyButton(
                text = "Ver fundaciones",
                onClick = onFundacionesClick
            )
        }

        PrettyCard(
            title = "Solicitudes",
            subtitle = "Consulta tus solicitudes de adopción",
            emoji = "📋"
        ) {
            InfoChip("Seguimiento de adopciones", "🐾")
            Spacer(modifier = Modifier.height(10.dp))
            PrettyButton(
                text = "Ver solicitudes",
                onClick = onSolicitudesClick
            )
        }

        PrettyCard(
            title = "Rescates",
            subtitle = "Animales rescatados recientemente",
            emoji = "🚑"
        ) {
            InfoChip("Historias de esperanza", "🌿")
            Spacer(modifier = Modifier.height(10.dp))
            PrettyButton(
                text = "Ver rescates",
                onClick = onRescatesClick
            )
        }

        PrettyCard(
            title = "Donaciones",
            subtitle = "Apoya económicamente a los refugios",
            emoji = "💚"
        ) {
            InfoChip("Toda ayuda cuenta", "💸")
            Spacer(modifier = Modifier.height(10.dp))
            PrettyButton(
                text = "Donar ahora",
                onClick = onDonacionesClick
            )
        }

        PrettyCard(
            title = "Mi Perfil",
            subtitle = "Gestiona tu información personal",
            emoji = "👤"
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            PrettyButton(
                text = "Abrir perfil",
                onClick = onPerfilClick
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "🐾 Gracias por apoyar la adopción responsable.",
            color = TextSecondary
        )
    }
}