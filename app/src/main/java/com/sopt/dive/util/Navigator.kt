package com.sopt.dive.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.sopt.dive.model.User
import com.sopt.dive.ui.screens.HomeScreen
import com.sopt.dive.ui.screens.ProfileScreen
import com.sopt.dive.ui.screens.SettingsScreen
import LoginScreen
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.sopt.dive.ui.screens.SignUpScreen


sealed class BottomNavItem(
    val route: Route,
    val label: String,
    val icon: ImageVector
) {
    data object HomeItem :
        BottomNavItem(Route.Home, "Home", Icons.Default.Home)

    data object ProfileItem :
        BottomNavItem(Route.Profile, "Profile", Icons.Default.Person)

    data object SettingsItem :
        BottomNavItem(Route.Settings, "Settings", Icons.Default.Settings)
}

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    var user by remember { mutableStateOf<User?>(null) }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottomBar = currentRoute !in listOf(Route.Login.name, Route.SignUp.name)
    val current = LocalContext.current
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
                            selected = currentRoute == item.route.name,
                            onClick = {
                                navController.navigate(item.route.name) {
                                    popUpTo(Route.Home.name) { saveState = true }
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
            startDestination = Route.Login.name
        ) {

            composable(Route.Login.name) {
                LoginScreen(
                    userData = user,
                    onLoginSuccess = { loggedInUser ->
                        user = loggedInUser
                        navController.navigate(Route.Home.name) {
                            popUpTo(Route.Login.name) { inclusive = true }
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(Route.SignUp.name)
                    }
                )
            }

            composable(Route.SignUp.name) {
                SignUpScreen(
                    onSignUpComplete = { signedUpUser ->
                        user = signedUpUser
                        Toast.makeText(
                            current,
                            "회원가입 완료! 로그인 화면으로 돌아갑니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    }
                )
            }

            composable(Route.Home.name) { HomeScreen(innerPadding) }
            composable(Route.Profile.name) { user?.let { ProfileScreen(innerPadding, it) } }
            composable(Route.Settings.name) { SettingsScreen(innerPadding) }
        }
    }
}
