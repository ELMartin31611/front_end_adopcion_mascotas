package com.adopcion.presentation.ui.mascotas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Button
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
import com.adopcion.domain.model.Mascota
import com.adopcion.domain.model.MascotaPayload
import com.adopcion.presentation.components.EmptyState
import com.adopcion.presentation.components.ErrorState
import com.adopcion.presentation.components.InfoChip
import com.adopcion.presentation.components.LoadingState
import com.adopcion.presentation.components.PawHeader
import com.adopcion.presentation.components.SectionTitle
import com.adopcion.presentation.components.StatusChip
import com.adopcion.presentation.viewmodel.MascotaUiState
import com.adopcion.presentation.viewmodel.MascotaViewModel
import com.adopcion.theme.Accent
import com.adopcion.theme.AccentDark
import com.adopcion.theme.AccentOnDark
import com.adopcion.theme.Background
import com.adopcion.theme.Surface
import com.adopcion.theme.TextPrimary
import com.adopcion.theme.TextSecondary

@Composable
fun MascotasScreen(
    viewModel: MascotaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var especie by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("Disponible") }
    var fundacionId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.cargarMascotas()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            PawHeader(
                title = "Mascotas",
                subtitle = "Perritos buscando una familia",
                emoji = "🐶"
            )
        }

        item {
            SectionTitle("Registrar mascota")
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
                        text = "Nuevo perrito en adopción 🐾",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )

                    Text(
                        text = "Completa la información básica para publicarlo.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary
                    )

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = especie,
                        onValueChange = { especie = it },
                        label = { Text("Especie") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = raza,
                        onValueChange = { raza = it },
                        label = { Text("Raza") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = edad,
                        onValueChange = { edad = it },
                        label = { Text("Edad") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    OutlinedTextField(
                        value = estado,
                        onValueChange = { estado = it },
                        label = { Text("Estado") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = fundacionId,
                        onValueChange = { fundacionId = it },
                        label = { Text("ID Fundación") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            viewModel.crearMascota(
                                MascotaPayload(
                                    nombre = nombre,
                                    especie = especie,
                                    raza = raza,
                                    edad = edad.toIntOrNull() ?: 0,
                                    descripcion = descripcion,
                                    estado = estado,
                                    fundacionId = fundacionId.toIntOrNull() ?: 1
                                )
                            )

                            nombre = ""
                            especie = ""
                            raza = ""
                            edad = ""
                            descripcion = ""
                            estado = "Disponible"
                            fundacionId = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                            contentColor = AccentOnDark
                        )
                    ) {
                        Text("Crear mascota")
                    }
                }
            }
        }

        item {
            SectionTitle("Mascotas registradas")
        }

        when (val s = state) {
            is MascotaUiState.Loading -> {
                item {
                    LoadingState("Cargando mascotas...")
                }
            }

            is MascotaUiState.Error -> {
                item {
                    ErrorState(
                        message = s.message,
                        onRetry = { viewModel.cargarMascotas() }
                    )
                }
            }

            is MascotaUiState.Success -> {
                if (s.mascotas.isEmpty()) {
                    item {
                        EmptyState(
                            emoji = "🐕",
                            title = "No hay mascotas",
                            message = "Aún no existen mascotas registradas."
                        )
                    }
                } else {
                    items(s.mascotas) { mascota ->
                        MascotaCard(
                            mascota = mascota,
                            onDelete = { viewModel.eliminarMascota(mascota.id) }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MascotaCard(
    mascota: Mascota,
    onDelete: () -> Unit
) {
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
                text = "🐕 ${mascota.nombre}",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary
            )

            Text(
                text = mascota.descripcion.ifBlank { "Sin descripción registrada." },
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoChip("ID: ${mascota.id}", "🔖")
                InfoChip(mascota.especie, "🐾")
                InfoChip(mascota.raza, "🌿")
                InfoChip("${mascota.edad} años", "🎂")
                InfoChip("Fundación ${mascota.fundacionId}", "🏡")
                StatusChip(mascota.estado)
            }

            OutlinedButton(
                onClick = onDelete,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AccentDark
                )
            ) {
                Text("Eliminar mascota")
            }
        }
    }
}