package com.adopcion.presentation.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdminDashboardScreen(
    onMascotasClick: () -> Unit,
    onFundacionesClick: () -> Unit,
    onRescatesClick: () -> Unit,
    onDonacionesClick: () -> Unit,
    onSolicitudesClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Panel Administrador",
            fontSize = 28.sp
        )

        Text(
            text = "Gestiona la información principal del sistema de adopción.",
            fontSize = 16.sp
        )

        AdminOptionButton(
            text = "Gestionar Mascotas",
            icon = Icons.Default.Pets,
            onClick = onMascotasClick
        )

        AdminOptionButton(
            text = "Gestionar Fundaciones",
            icon = Icons.Default.Business,
            onClick = onFundacionesClick
        )

        AdminOptionButton(
            text = "Gestionar Rescates",
            icon = Icons.Default.VolunteerActivism,
            onClick = onRescatesClick
        )

        AdminOptionButton(
            text = "Ver Donaciones",
            icon = Icons.Default.AttachMoney,
            onClick = onDonacionesClick
        )

        AdminOptionButton(
            text = "Gestionar Solicitudes",
            icon = Icons.Default.Description,
            onClick = onSolicitudesClick
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onLogoutClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Cerrar sesión")
        }
    }
}

@Composable
private fun AdminOptionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text)
    }
}