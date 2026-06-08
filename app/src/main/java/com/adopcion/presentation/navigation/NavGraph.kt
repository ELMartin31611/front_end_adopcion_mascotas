package com.adopcion.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adopcion.presentation.ui.admin.AdminDashboardScreen
import com.adopcion.presentation.ui.auth.LoginScreen
import com.adopcion.presentation.ui.auth.RegisterScreen
import com.adopcion.presentation.ui.donaciones.DonacionesScreen
import com.adopcion.presentation.ui.fundaciones.FundacionesScreen
import com.adopcion.presentation.ui.home.HomeScreen
import com.adopcion.presentation.ui.mascotas.MascotasScreen
import com.adopcion.presentation.ui.perfil.PerfilScreen
import com.adopcion.presentation.ui.rescates.RescatesScreen
import com.adopcion.presentation.ui.solicitudes.SolicitudesScreen
import com.adopcion.presentation.viewmodel.AuthViewModel



@Composable
fun NavGraph(
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()

    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val isStaff by authViewModel.isStaff.collectAsState()
    val isCheckingSession by authViewModel.isCheckingSession.collectAsState()

    if (isCheckingSession) return

    val startDestination = when {
        !isAuthenticated -> Screen.Login.route
        isStaff -> Screen.AdminDashboard.route
        else -> Screen.Home.route
    }

    Scaffold(
        bottomBar = {
            val currentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            val showBottomBar = currentRoute in listOf(
                Screen.Home.route,
                Screen.Mascotas.route,
                Screen.Fundaciones.route,
                Screen.Solicitudes.route,
                Screen.Rescates.route,
                Screen.Donaciones.route,
                Screen.Perfil.route,
            )

            if (showBottomBar) {
                BottomNavBar(navController = navController)
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding)
        ) {

            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginSuccess = { staff ->
                        navController.navigate(
                            if (staff) Screen.AdminDashboard.route else Screen.Home.route
                        ) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    viewModel = authViewModel
                )
            }

            composable(Screen.Register.route) {
                RegisterScreen(
                    onRegisterSuccess = { staff ->
                        navController.navigate(
                            if (staff) Screen.AdminDashboard.route else Screen.Home.route
                        ) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route)
                    },
                    viewModel = authViewModel
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    onMascotasClick = { navController.navigate(Screen.Mascotas.route) },
                    onFundacionesClick = { navController.navigate(Screen.Fundaciones.route) },
                    onSolicitudesClick = { navController.navigate(Screen.Solicitudes.route) },
                    onRescatesClick = { navController.navigate(Screen.Rescates.route) },
                    onDonacionesClick = { navController.navigate(Screen.Donaciones.route) },
                    onPerfilClick = { navController.navigate(Screen.Perfil.route) },
                )
            }

            composable(Screen.Mascotas.route) {
                MascotasScreen()
            }

            composable(Screen.Fundaciones.route) {
                FundacionesScreen()
            }

            composable(Screen.Solicitudes.route) {
                SolicitudesScreen()
            }

            composable(Screen.Rescates.route) {
                RescatesScreen()
            }

            composable(Screen.Donaciones.route) {
                DonacionesScreen()
            }

            composable(Screen.Perfil.route) {
                PerfilScreen(
                    onLogout = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.AdminDashboard.route) {
                AdminDashboardScreen()
            }
        }
    }
}