package com.johnhaynes.fintrack.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnhaynes.fintrack.data.database.DatabaseBuilder
import com.johnhaynes.fintrack.data.entity.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Handles searching through transactions using a search bar.
 */
class SearchViewModel : ViewModel() {

    private val _results = MutableStateFlow<List<Transaction>>(emptyList())
    val results: StateFlow<List<Transaction>> = _results

    fun search(context: Context, query: String) {
        val db = DatabaseBuilder.getDatabase(context)
        val transactionDao = db.transactionDao()

        viewModelScope.launch {
            val allTransactions = transactionDao.getAllTransactions()

            val filtered = allTransactions.filter { transaction ->
                transaction.description.contains(query, ignoreCase = true)
            }

            _results.value = filtered
        }
    }
}
