package com.johnhaynes.fintrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.johnhaynes.fintrack.ui.AppNavHost
import com.johnhaynes.fintrack.ui.theme.FinTrackTheme
import com.johnhaynes.fintrack.viewmodel.AuthViewModel
import com.johnhaynes.fintrack.viewmodel.DashboardViewModel
import com.johnhaynes.fintrack.viewmodel.SearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FinTrackTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    // Navigation controller
                    val navController = rememberNavController()

                    // ViewModels
                    val authViewModel = AuthViewModel()
                    val dashboardViewModel = DashboardViewModel()
                    val searchViewModel = SearchViewModel()

                    // Navigation host
                    AppNavHost(
                        navController = navController,
                        context = this,
                        authViewModel = authViewModel,
                        dashboardViewModel = dashboardViewModel,
                        searchViewModel = searchViewModel
                    )
                }
            }
        }
    }
}

