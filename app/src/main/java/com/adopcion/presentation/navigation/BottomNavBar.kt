package com.adopcion.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavItem(
    val screen: Screen,
    val label: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
)

@Composable
fun BottomNavBar(
    navController: NavController,
) {
    val items = listOf(
        BottomNavItem(Screen.Home, "Inicio", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem(Screen.Mascotas, "Mascotas", Icons.Filled.Pets, Icons.Outlined.Pets),
        BottomNavItem(Screen.Fundaciones, "Fundaciones", Icons.Filled.Business, Icons.Outlined.Business),
        BottomNavItem(Screen.Solicitudes, "Solicitudes", Icons.Filled.List, Icons.Outlined.List),
        BottomNavItem(Screen.Perfil, "Perfil", Icons.Filled.Person, Icons.Outlined.Person),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == item.screen.route)
                            item.selectedIcon
                        else
                            item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}