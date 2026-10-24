package com.johnhaynes.fintrack.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.johnhaynes.fintrack.viewmodel.AuthViewModel
import com.johnhaynes.fintrack.viewmodel.DashboardViewModel
import com.johnhaynes.fintrack.viewmodel.SearchViewModel
import com.johnhaynes.fintrack.ui.screens.auth.LoginScreen
import com.johnhaynes.fintrack.ui.screens.auth.RegisterScreen
import com.johnhaynes.fintrack.ui.screens.auth.ChangePasswordScreen
import com.johnhaynes.fintrack.ui.screens.dashboard.DashboardScreen
import com.johnhaynes.fintrack.ui.screens.search.SearchScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    context: Context,
    authViewModel: AuthViewModel,
    dashboardViewModel: DashboardViewModel,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN
    ) {

        composable(NavRoutes.LOGIN) {
            LoginScreen(
                context = context,
                authViewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(NavRoutes.DASHBOARD)
                }
            )
        }

        composable(NavRoutes.REGISTER) {
            RegisterScreen(
                context = context,
                authViewModel = authViewModel,
                onRegisterSuccess = {
                    navController.navigate(NavRoutes.LOGIN)
                }
            )
        }

        composable(NavRoutes.CHANGE_PASSWORD) {
            ChangePasswordScreen(
                context = context,
                authViewModel = authViewModel,
                userId = 1, // You can replace this with real user ID later
                onPasswordChanged = {
                    navController.navigate(NavRoutes.LOGIN)
                }
            )
        }

        composable(NavRoutes.DASHBOARD) {
            DashboardScreen(
                context = context,
                dashboardViewModel = dashboardViewModel
            )
        }

        composable(NavRoutes.SEARCH) {
            SearchScreen(
                context = context,
                searchViewModel = searchViewModel
            )
        }
    }
}
