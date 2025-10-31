package com.sopt.dive.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.*
import com.sopt.dive.ui.screens.HomeScreen
import com.sopt.dive.ui.screens.ProfileScreen
import com.sopt.dive.ui.screens.SettingsScreen
import com.sopt.dive.model.User

sealed class BottomNavItem(
    val route: Any,
    val label: String,
    val icon: ImageVector
) {
    data object SettingsItem : BottomNavItem(Settings, "Settings", Icons.Default.Settings)

    data object HomeItem : BottomNavItem(Home, "Home", Icons.Default.Home)
    data object ProfileItem : BottomNavItem(Profile, "Profile", Icons.Default.Person)


}


@Composable
fun Navigator(user: User) {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.SettingsItem,
        BottomNavItem.HomeItem,
        BottomNavItem.ProfileItem,

        )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route::class.qualifiedName,
                        onClick = { navController.navigate(item.route) },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home
        ) {
            composable<Settings> { SettingsScreen(innerPadding) }
            composable<Home> { HomeScreen(innerPadding) }
            composable<Profile> { ProfileScreen(innerPadding, user) }

        }

    }
}

