package com.adopcion.presentation.ui.donaciones

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.presentation.viewmodel.DonacionViewModel

@Composable
fun DonacionesScreen(
    viewModel: DonacionViewModel = hiltViewModel()
) {
    val donaciones by viewModel.donaciones.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDonaciones()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(donaciones) { donacion ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "Donación #${donacion.id}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Usuario ID: ${donacion.usuarioId}")

                    Text("Fundación ID: ${donacion.fundacionId}")

                    Text("Monto: $${donacion.monto}")

                    Text("Fecha: ${donacion.fecha}")
                }
            }
        }
    }
}