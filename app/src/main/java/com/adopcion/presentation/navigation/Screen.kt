package com.adopcion.presentation.navigation

sealed class Screen(val route: String) {

    // Públicas
    object Home : Screen("home")
    object Mascotas : Screen("mascotas")
    object Solicitudes : Screen("solicitudes")
    object Fundaciones : Screen("fundaciones")
    object Rescates : Screen("rescates")
    object Donaciones : Screen("donaciones")
    object Perfil : Screen("perfil")
}