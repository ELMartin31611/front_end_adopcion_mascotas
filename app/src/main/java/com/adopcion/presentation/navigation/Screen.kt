package com.adopcion.presentation.navigation

sealed class Screen(val route: String) {

    // Auth
    data object Login    : Screen("login")
    data object Register : Screen("register")

    // Público
    data object Home     : Screen("home")
    data object Mascotas : Screen("mascotas")
    data class  MascotaDetail(val id: Int = 0) : Screen("mascota/{id}") {
        fun createRoute(id: Int) = "mascota/$id"
    }

    // Usuario
    data object Solicitudes : Screen("solicitudes")
    data class  SolicitudDetail(val id: Int = 0) : Screen("solicitud/{id}") {
        fun createRoute(id: Int) = "solicitud/$id"
    }
    data object Perfil : Screen("perfil")

    // Admin
    data object AdminDashboard : Screen("admin")
    data object AdminMascotas  : Screen("admin/mascotas")
    data object AdminRescates  : Screen("admin/rescates")
    data object AdminUsuarios  : Screen("admin/usuarios")
}