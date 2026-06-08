package com.adopcion.presentation.ui.fundaciones

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
import com.adopcion.domain.model.FundacionPayload
import com.adopcion.presentation.components.EmptyState
import com.adopcion.presentation.components.ErrorState
import com.adopcion.presentation.components.InfoChip
import com.adopcion.presentation.components.LoadingState
import com.adopcion.presentation.components.PawHeader
import com.adopcion.presentation.components.SectionTitle
import com.adopcion.presentation.components.StatusChip
import com.adopcion.presentation.viewmodel.FundacionViewModel
import com.adopcion.theme.Accent
import com.adopcion.theme.AccentDark
import com.adopcion.theme.AccentOnDark
import com.adopcion.theme.Surface
import com.adopcion.theme.TextPrimary
import com.adopcion.theme.TextSecondary

@Composable
fun FundacionesScreen(
    viewModel: FundacionViewModel = hiltViewModel()
) {
    val fundaciones by viewModel.fundaciones.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadFundaciones()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            PawHeader(
                title = "Fundaciones",
                subtitle = "Refugios y aliados que ayudan a los animalitos",
                emoji = "🏡"
            )
        }

        item {
            SectionTitle("Registrar fundación")
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
                        text = "Nueva fundación 🤝",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary
                    )

                    Text(
                        text = "Agrega refugios o centros de apoyo para adopciones y donaciones.",
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
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2
                    )

                    OutlinedTextField(
                        value = direccion,
                        onValueChange = { direccion = it },
                        label = { Text("Dirección") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = telefono,
                        onValueChange = { telefono = it },
                        label = { Text("Teléfono") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            viewModel.crearFundacion(
                                FundacionPayload(
                                    nombre = nombre,
                                    descripcion = descripcion,
                                    direccion = direccion,
                                    telefono = telefono,
                                    correo = correo,
                                    isActive = true
                                )
                            )

                            nombre = ""
                            descripcion = ""
                            direccion = ""
                            telefono = ""
                            correo = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.extraLarge,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Accent,
                            contentColor = AccentOnDark
                        )
                    ) {
                        Text("Crear fundación")
                    }
                }
            }
        }

        item {
            SectionTitle("Fundaciones registradas")
        }

        when {
            loading -> {
                item {
                    LoadingState("Cargando fundaciones...")
                }
            }

            error != null -> {
                item {
                    ErrorState(
                        message = error ?: "Error al cargar fundaciones",
                        onRetry = { viewModel.loadFundaciones() }
                    )
                }
            }

            fundaciones.isEmpty() -> {
                item {
                    EmptyState(
                        emoji = "🏡",
                        title = "No hay fundaciones",
                        message = "Aún no existen fundaciones registradas."
                    )
                }
            }

            else -> {
                items(fundaciones) { fundacion ->
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
                                text = "🏡 ${fundacion.nombre}",
                                style = MaterialTheme.typography.titleLarge,
                                color = TextPrimary
                            )

                            Text(
                                text = fundacion.descripcion.ifBlank { "Sin descripción registrada." },
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )

                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                InfoChip("ID: ${fundacion.id}", "🔖")
                                InfoChip(fundacion.direccion, "📍")
                                InfoChip(fundacion.telefono, "📞")
                                InfoChip(fundacion.correo, "📧")
                                StatusChip("Activo")
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            OutlinedButton(
                                onClick = {
                                    viewModel.eliminarFundacion(fundacion.id)
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = MaterialTheme.shapes.extraLarge,
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = AccentDark
                                )
                            ) {
                                Text("Eliminar fundación")
                            }
                        }
                    }
                }
            }
        }
    }
}