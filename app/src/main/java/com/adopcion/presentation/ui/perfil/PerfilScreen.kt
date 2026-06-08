package com.adopcion.presentation.ui.perfil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adopcion.domain.model.LoggedUser
import com.adopcion.presentation.components.InfoChip
import com.adopcion.presentation.components.PawHeader
import com.adopcion.presentation.components.SectionTitle
import com.adopcion.presentation.components.StatusChip
import com.adopcion.theme.AccentDark
import com.adopcion.theme.Surface
import com.adopcion.theme.TextPrimary
import com.adopcion.theme.TextSecondary

@Composable
fun PerfilScreen(
    user: LoggedUser?,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        PawHeader(
            title = "Mi Perfil",
            subtitle = "Tu cuenta dentro del refugio",
            emoji = "👤"
        )

        SectionTitle("Datos personales")

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
                    text = "Información de usuario",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextPrimary
                )

                Text(
                    text = "Estos son los datos guardados de tu sesión.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )

                InfoChip(
                    text = user?.username ?: "No disponible",
                    emoji = "👤"
                )

                InfoChip(
                    text = user?.email ?: "No disponible",
                    emoji = "📧"
                )

                StatusChip(
                    text = if (user?.isStaff == true) "Administrador" else "Usuario"
                )
            }
        }

        SectionTitle("Mensaje")

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = Surface),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "🐶 Gracias por formar parte de esta causa.",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Cada adopción, donación o colaboración ayuda a que más mascotas tengan una segunda oportunidad.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = AccentDark
            )
        ) {
            Text("Cerrar sesión")
        }
    }
}