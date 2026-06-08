package com.adopcion.presentation.ui.rescates

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.domain.model.RescatePayload
import com.adopcion.presentation.components.EmptyState
import com.adopcion.presentation.components.ErrorState
import com.adopcion.presentation.components.InfoChip
import com.adopcion.presentation.components.LoadingState
import com.adopcion.presentation.components.PawHeader
import com.adopcion.presentation.components.SectionTitle
import com.adopcion.presentation.components.StatusChip
import com.adopcion.presentation.viewmodel.RescateViewModel
import com.adopcion.theme.Accent
import com.adopcion.theme.AccentDark
import com.adopcion.theme.AccentOnDark
import com.adopcion.theme.Surface
import com.adopcion.theme.TextPrimary
import com.adopcion.theme.TextSecondary

@Composable
fun RescatesScreen(
    viewModel: RescateViewModel = hiltViewModel()
) {
    val rescates by viewModel.rescates.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    var descripcion by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("Pendiente") }

    LaunchedEffect(Unit) {
        viewModel.loadRescates()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            PawHeader(
                title = "Rescates",
                subtitle = "Casos de animalitos que necesitan ayuda",
                emoji = "🚑"
            )
        }

        item {
            SectionTitle("Registrar rescate")
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(containerColor = Surface),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Nuevo rescate 🐾",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )

                    Text(
                        text = "Registra un caso para darle seguimiento.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    OutlinedTextField(
                        value = ubicacion,
                        onValueChange = { ubicacion = it },
                        label = { Text("Ubicación") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = estado,
                        onValueChange = { estado = it },
                        label = { Text("Estado") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            viewModel.crearRescate(
                                RescatePayload(
                                    descripcion = descripcion,
                                    ubicacion = ubicacion,
                                    estado = estado
                                )
                            )

                            descripcion = ""
                            ubicacion = ""
                            estado = "Pendiente"
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                            contentColor = AccentOnDark
                        )
                    ) {
                        Text("Crear rescate")
                    }
                }
            }
        }

        item {
            SectionTitle("Rescates registrados")
        }

        when {
            loading -> {
                item {
                    LoadingState("Cargando rescates...")
                }
            }

            error != null -> {
                item {
                    ErrorState(
                        message = error ?: "Error al cargar rescates",
                        onRetry = { viewModel.loadRescates() }
                    )
                }
            }

            rescates.isEmpty() -> {
                item {
                    EmptyState(
                        emoji = "🐕",
                        title = "No hay rescates",
                        message = "Aún no existen rescates registrados."
                    )
                }
            }

            else -> {
                items(rescates) { rescate ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.large,
                        colors = CardDefaults.cardColors(containerColor = Surface),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = "🚑 Rescate #${rescate.id}",
                                style = MaterialTheme.typography.titleLarge,
                                color = TextPrimary
                            )

                            Text(
                                text = rescate.descripcion.ifBlank { "Sin descripción registrada." },
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )

                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                InfoChip("Ubicación: ${rescate.ubicacion}", "📍")
                                InfoChip("Fecha: ${rescate.createdAt}", "📅")
                                StatusChip(rescate.estado)
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            OutlinedButton(
                                onClick = {
                                    viewModel.eliminarRescate(rescate.id)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = MaterialTheme.shapes.extraLarge,
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = AccentDark
                                )
                            ) {
                                Text("Eliminar rescate")
                            }
                        }
                    }
                }
            }
        }
    }
}