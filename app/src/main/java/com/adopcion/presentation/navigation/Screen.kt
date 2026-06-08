package com.adopcion.presentation.navigation

sealed class Screen(val route: String) {

    // Auth
    object Login : Screen("login")
    object Register : Screen("register")

    // Usuario
    object Home : Screen("home")
    object Mascotas : Screen("mascotas")
    object Fundaciones : Screen("fundaciones")
    object Solicitudes : Screen("solicitudes")
    object Rescates : Screen("rescates")
    object Donaciones : Screen("donaciones")
    object Perfil : Screen("perfil")

    // Admin
    object AdminDashboard : Screen("admin_dashboard")
}