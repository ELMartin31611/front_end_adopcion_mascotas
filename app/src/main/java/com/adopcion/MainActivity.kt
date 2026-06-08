package com.adopcion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.adopcion.presentation.auth.LoginScreen
import com.adopcion.presentation.viewmodel.AuthViewModel
import com.adopcion.theme.AdopcionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AdopcionTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val viewModel: AuthViewModel = hiltViewModel()

                    LoginScreen(viewModel = viewModel)
                }
            }
        }
    }
}