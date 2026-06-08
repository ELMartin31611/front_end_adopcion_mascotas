package com.adopcion.presentation.ui.donaciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.presentation.components.EmptyState
import com.adopcion.presentation.components.InfoChip
import com.adopcion.presentation.components.PawHeader
import com.adopcion.presentation.components.SectionTitle
import com.adopcion.theme.Surface
import com.adopcion.theme.TextPrimary
import com.adopcion.theme.TextSecondary
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            PawHeader(
                title = "Donaciones",
                subtitle = "Gracias por apoyar a los animales",
                emoji = "💚"
            )
        }

        item {
            SectionTitle("Historial de donaciones")
        }

        if (donaciones.isEmpty()) {

            item {
                EmptyState(
                    emoji = "💸",
                    title = "Sin donaciones",
                    message = "Todavía no existen donaciones registradas."
                )
            }

        } else {

            items(donaciones) { donacion ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = Surface
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Text(
                            text = "💚 Donación #${donacion.id}",
                            style = MaterialTheme.typography.titleLarge,
                            color = TextPrimary
                        )

                        Text(
                            text = "Aporte realizado para ayudar a los refugios y mascotas.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )

                        InfoChip(
                            text = "Usuario ${donacion.usuarioId}",
                            emoji = "👤"
                        )

                        InfoChip(
                            text = "Fundación ${donacion.fundacionId}",
                            emoji = "🏡"
                        )

                        InfoChip(
                            text = "$${donacion.monto}",
                            emoji = "💰"
                        )

                        InfoChip(
                            text = donacion.fecha,
                            emoji = "📅"
                        )
                    }
                }
            }
        }
    }
}