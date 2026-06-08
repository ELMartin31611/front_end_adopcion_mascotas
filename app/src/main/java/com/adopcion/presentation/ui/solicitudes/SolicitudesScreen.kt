package com.adopcion.presentation.ui.solicitudes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.domain.model.Solicitud
import com.adopcion.presentation.viewmodel.SolicitudViewModel
import com.adopcion.theme.*

@Composable
fun SolicitudesScreen(
    viewModel: SolicitudViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Solicitudes",
                style = MaterialTheme.typography.headlineLarge
            )

            IconButton(onClick = { viewModel.cargarSolicitudes() }) {
                Icon(Icons.Default.Refresh, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (state.loading) {
            CircularProgressIndicator()
        }

        state.error?.let { error ->
            Text(
                text = "❌ $error",
                color = Error
            )
        }

        if (!state.loading && state.solicitudes.isEmpty()) {
            Text(
                text = "Todavía no hay solicitudes registradas.",
                color = TextSecondary
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.solicitudes) { solicitud ->
                SolicitudCard(
                    solicitud = solicitud,
                    onDelete = {
                        viewModel.eliminarSolicitud(solicitud.id)
                    }
                )
            }
        }
    }
}

@Composable
private fun SolicitudCard(
    solicitud: Solicitud,
    onDelete: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Surface
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Icon(
                    Icons.Default.TaskAlt,
                    contentDescription = null,
                    tint = Accent
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Solicitud #${solicitud.id}",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Usuario: ${solicitud.usuario}")
            Text("Mascota ID: ${solicitud.mascotaId}")
            Text("Estado: ${solicitud.estado}")
            Text("Fecha: ${solicitud.fecha}")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onDelete,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Eliminar solicitud")
            }
        }
    }
}