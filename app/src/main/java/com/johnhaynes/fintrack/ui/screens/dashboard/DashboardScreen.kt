package com.johnhaynes.fintrack.ui.screens.dashboard

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johnhaynes.fintrack.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    context: Context,
    dashboardViewModel: DashboardViewModel
) {
    // Load dashboard data when screen opens
    LaunchedEffect(Unit) {
        dashboardViewModel.loadDashboardData(context)
    }

    val income by dashboardViewModel.totalIncome.collectAsState()
    val expenses by dashboardViewModel.totalExpenses.collectAsState()
    val balance by dashboardViewModel.balance.collectAsState()
    val transactions by dashboardViewModel.transactions.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Totals Section
        Text(text = "Total Income: $${String.format("%.2f", income)}")
        Text(text = "Total Expenses: $${String.format("%.2f", expenses)}")
        Text(text = "Balance: $${String.format("%.2f", balance)}")

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Recent Transactions",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(transactions) { transaction ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = transaction.description)
                        Text(text = "Amount: $${transaction.amount}")
                        Text(text = "Category ID: ${transaction.categoryId}")
                    }
                }
            }
        }
    }
}

