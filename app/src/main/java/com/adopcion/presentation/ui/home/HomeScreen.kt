package com.adopcion.presentation.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = "Sistema de Adopción",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onMascotasClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mascotas")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onFundacionesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fundaciones")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSolicitudesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Solicitudes")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onRescatesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Rescates")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onDonacionesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Donaciones")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onPerfilClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Perfil")
        }
    }
}