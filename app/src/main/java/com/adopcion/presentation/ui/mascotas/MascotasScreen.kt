package com.adopcion.presentation.ui.mascotas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.domain.model.MascotaPayload
import com.adopcion.presentation.viewmodel.MascotaUiState
import com.adopcion.presentation.viewmodel.MascotaViewModel

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Mascotas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

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
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.height(8.dp))

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Mascota")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val s = state) {
            is MascotaUiState.Loading -> {
                CircularProgressIndicator()
            }

            is MascotaUiState.Error -> {
                Text("Error: ${s.message}")
            }

            is MascotaUiState.Success -> {
                LazyColumn {
                    items(s.mascotas) { mascota ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text("ID: ${mascota.id}")
                                Text("Nombre: ${mascota.nombre}")
                                Text("Especie: ${mascota.especie}")
                                Text("Raza: ${mascota.raza}")
                                Text("Edad: ${mascota.edad}")
                                Text("Estado: ${mascota.estado}")
                                Text("Fundación: ${mascota.fundacionId}")

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = {
                                        viewModel.eliminarMascota(mascota.id)
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