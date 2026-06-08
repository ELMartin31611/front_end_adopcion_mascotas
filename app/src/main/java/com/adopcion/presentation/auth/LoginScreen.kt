package com.adopcion.presentation.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adopcion.presentation.ui.auth.AuthUiState
import com.adopcion.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(24.dp)) {

        Text("Login", fontSize = 28.sp)

        OutlinedTextField(
            value = user,
            onValueChange = { user = it },
            label = { Text("Usuario") }
        )

        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") }
        )

        Button(
            onClick = { viewModel.login(user, pass) }
        ) {
            Text("Ingresar")
        }

        // ✅ ERROR
        if (uiState is AuthUiState.Error) {
            Text(
                "❌ ${(uiState as AuthUiState.Error).message}",
                color = Color.Red
            )
        }

        // ✅ LOADING
        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator()
        }
    }
}