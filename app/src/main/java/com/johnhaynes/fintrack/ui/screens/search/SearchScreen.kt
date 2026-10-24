package com.johnhaynes.fintrack.ui.screens.search

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johnhaynes.fintrack.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    context: Context,
    searchViewModel: SearchViewModel
) {
    var query by remember { mutableStateOf("") }

    val results by searchViewModel.results.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Search Transactions",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                searchViewModel.search(context, query)
            },
            label = { Text("Search...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(results) { transaction ->
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
