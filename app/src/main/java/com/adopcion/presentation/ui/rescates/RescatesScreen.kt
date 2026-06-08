package com.adopcion.presentation.ui.rescates

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.presentation.viewmodel.RescateViewModel

@Composable
fun RescatesScreen(
    viewModel: RescateViewModel = hiltViewModel()
) {
    val rescates by viewModel.rescates.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRescates()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(rescates) { rescate ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = rescate.descripcion,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(rescate.ubicacion)

                    Text(rescate.estado)
                }
            }
        }
    }
}