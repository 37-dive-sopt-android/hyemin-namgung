package com.sopt.dive.util

import Route
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.model.User
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.viewmodel.MainViewModel
import com.sopt.dive.ui.screens.HomeScreen
import com.sopt.dive.ui.screens.LoginScreen
import com.sopt.dive.ui.screens.ProfileScreen
import com.sopt.dive.ui.screens.CardScreen
import com.sopt.dive.ui.screens.SignUpScreen

sealed class BottomNavItem(
    val route: Route,
    val label: String,
    val icon: ImageVector
) {
    data object HomeItem : BottomNavItem(
        Route.Home,
        "Home",
        Icons.Default.Home
    )

    data object ProfileItem : BottomNavItem(
        Route.Profile,
        "Profile",
        Icons.Default.Person
    )

    data object SettingsItem : BottomNavItem(
        Route.Settings,
        "Settings",
        Icons.Default.Settings
    )
}

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    val mainViewModel: MainViewModel = viewModel()
    var user by remember { mutableStateOf<User?>(null) }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottomBar = currentRoute !in listOf(Route.Login.path, Route.SignUp.path)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    val items = listOf(
                        BottomNavItem.HomeItem,
                        BottomNavItem.ProfileItem,
                        BottomNavItem.SettingsItem
                    )

                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route.path, // currentRoute와 item.route.path를 비교
                            onClick = {
                                navController.navigate(item.route.path) {
                                    popUpTo(Route.Home.path) {
                                        saveState = true
                                    }
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
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Login.path
        ) {
            composable(Route.Login.path) {
                LoginScreen(
                    mainViewModel = mainViewModel,
                    onLoginSuccess = { userName, password ->
                        val loggedInUser = mainViewModel.currentUser
                        if (loggedInUser != null && loggedInUser.id == userName && loggedInUser.pw == password) {
                            user = loggedInUser
                            navController.navigate(Route.Home.path) {
                                popUpTo(Route.Login.path) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(Route.SignUp.path)
                    }
                )
            }

            composable(Route.SignUp.path) {
                SignUpScreen(
                    mainViewModel = mainViewModel,
                    onSignUpComplete = { user ->
                        navController.popBackStack(Route.Login.path, inclusive = false)
                    }
                )
            }

            composable(Route.Home.path) {
                HomeScreen(innerPadding)
            }

            composable(Route.Profile.path) {
                user?.let { ProfileScreen(innerPadding, it) }
            }

            composable(Route.Settings.path) {
                CardScreen(innerPadding)
            }
        }
    }
}
