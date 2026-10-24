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
 * Handles dashboard totals and transaction list loading.
 */
class DashboardViewModel : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    private val _totalIncome = MutableStateFlow(0.0)
    val totalIncome: StateFlow<Double> = _totalIncome

    private val _totalExpenses = MutableStateFlow(0.0)
    val totalExpenses: StateFlow<Double> = _totalExpenses

    private val _balance = MutableStateFlow(0.0)
    val balance: StateFlow<Double> = _balance

    fun loadDashboardData(context: Context) {
        val db = DatabaseBuilder.getDatabase(context)
        val transactionDao = db.transactionDao()

        viewModelScope.launch {
            val list = transactionDao.getAllTransactions()
            _transactions.value = list

            val income = list.filter { it.amount > 0 }.sumOf { it.amount }
            val expenses = list.filter { it.amount < 0 }.sumOf { it.amount }

            _totalIncome.value = income
            _totalExpenses.value = expenses
            _balance.value = income + expenses
        }
    }
}
