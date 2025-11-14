package com.sopt.dive.util

import Route
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.ui.screens.HomeScreen
import com.sopt.dive.ui.screens.LoginScreen
import com.sopt.dive.ui.screens.ProfileScreen
import com.sopt.dive.ui.screens.CardScreen
import com.sopt.dive.ui.screens.SignUpScreen
import com.sopt.dive.viewmodel.UserViewModel

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

    companion object {
        val items = listOf(HomeItem, ProfileItem, SettingsItem)
    }
}

@Composable
fun Navigator(navController: NavHostController = rememberNavController()) {
    val userViewModel: UserViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                UserViewModel(createSavedStateHandle())
            }
        }
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottomBar = currentRoute !in listOf(Route.Login.path, Route.SignUp.path)

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    BottomNavItem.items.forEach { item ->
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
                            label = { Text(item.label) })
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
                val loginSuccess by userViewModel.loginSuccess.collectAsState()

                LoginScreen(
                    userViewModel = userViewModel,
                    onLoginSuccess = {
                        navController.navigate(Route.Home.path) {
                            popUpTo(Route.Login.path) { inclusive = true }
                            launchSingleTop = true
                        }

                    },
                    onSignUpClick = {
                        navController.navigate(Route.SignUp.path)
                    }

                )
                val context = LocalContext.current
                LaunchedEffect(loginSuccess) {
                    when (loginSuccess) {
                        true -> {
                            navController.navigate(Route.Home.path) {
                                popUpTo(Route.Login.path) { inclusive = true }
                                launchSingleTop = true
                            }
                        }

                        false -> {
                            Toast.makeText(context, "로그인 실패 ", Toast.LENGTH_SHORT).show()
                        }

                        null -> ""
                    }
                }
            }

            composable(Route.SignUp.path) {
                SignUpScreen(
                    userViewModel = userViewModel,
                    onSignUpComplete =
                        {
                            navController.popBackStack(Route.Login.path, inclusive = false)
                        }
                )
            }

            composable(Route.Home.path) {
                HomeScreen(innerPadding)
            }

            composable(Route.Profile.path) {
                val loggedInUserId by userViewModel.loggedInUserId.collectAsState()
                ProfileScreen(innerPadding, userViewModel)

                LaunchedEffect(loggedInUserId) {
                    loggedInUserId?.let { userViewModel.fetchUser(it) }
                }
            }

            composable(Route.Settings.path) {
                CardScreen(innerPadding)
            }
        }
    }
}
