package com.adopcion.presentation.ui.fundaciones

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.domain.model.FundacionPayload
import com.adopcion.presentation.viewmodel.FundacionViewModel

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Fundaciones",
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
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
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

        Spacer(modifier = Modifier.height(8.dp))

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
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear Fundación")
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

                    items(fundaciones) { fundacion ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    fundacion.nombre,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text(fundacion.direccion)
                                Text(fundacion.telefono)
                                Text(fundacion.correo)

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = {
                                        viewModel.eliminarFundacion(
                                            fundacion.id
                                        )
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