package com.adopcion.presentation.ui.rescates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.domain.model.RescatePayload
import com.adopcion.presentation.viewmodel.RescateViewModel

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Rescates",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.height(8.dp))

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Rescate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            loading -> {
                CircularProgressIndicator()
            }

            error != null -> {
                Text("Error: $error")
            }

            else -> {
                LazyColumn {
                    items(rescates) { rescate ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text("ID: ${rescate.id}")
                                Text("Descripción: ${rescate.descripcion}")
                                Text("Ubicación: ${rescate.ubicacion}")
                                Text("Estado: ${rescate.estado}")
                                Text("Fecha: ${rescate.createdAt}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = {
                                        viewModel.eliminarRescate(rescate.id)
                                    }
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}