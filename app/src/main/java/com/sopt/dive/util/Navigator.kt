package com.sopt.dive.util

import android.provider.ContactsContract.Profile
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
import com.sopt.dive.util.Route.Home
import com.sopt.dive.util.Route.Profile
import com.sopt.dive.util.Route.Settings

sealed class BottomNavItem(
    val route: Route,
    val label: String,
    val icon: ImageVector
) {
    data object SettingsItem :
        BottomNavItem(route = Settings, label = "Settings", icon = Icons.Default.Settings)

    data object HomeItem :
        BottomNavItem(route = Home, label = "Home", icon = Icons.Default.Home)

    data object ProfileItem :
        BottomNavItem(route = Profile, label = "Profile", icon = Icons.Default.Person)
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
                        selected = currentRoute == item.route.name::class.qualifiedName,
                        onClick = {
                            navController.navigate(item.route.name) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home.name
        ) {
            composable(Settings.name) { SettingsScreen(innerPadding) }
            composable(Home.name) { HomeScreen(innerPadding) }
            composable(Profile.name) { ProfileScreen(innerPadding, user) }

        }

    }
}

