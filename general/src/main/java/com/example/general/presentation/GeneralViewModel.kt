package com.example.general.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.general.data.repository.GeneralRepository
import com.example.general.presentation.models.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class GeneralViewModel(
    private val repository: GeneralRepository
): ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _expensesTotalByPeriods = MutableLiveData<List<Double>>()
    val expensesTotalByPeriods: LiveData<List<Double>> = _expensesTotalByPeriods

    private val _incomeMonthTotal = MutableLiveData<List<Double>>()
    val incomeMonthTotal: LiveData<List<Double>> = _incomeMonthTotal

    init {
        getExpenses()
        getIncomes()
    }

    private fun getExpenses() {
        scope.launch {
            repository.getExpenses().collect { transactions ->
                val today = calculateSumForDay(transactions)
                val week = calculateSumForWeek(transactions)
                val month = calculateSumForMonth(transactions)

                val expensesTotalList = listOf(today, week, month)
                _expensesTotalByPeriods.postValue(expensesTotalList)
            }
        }
    }

    private fun getIncomes() {
        scope.launch {
            repository.getIncomes().collect { transactions ->
                _incomeMonthTotal.postValue(
                    listOf(
                        calculateSumForMonth(transactions)
                    )
                )
            }
        }
    }

    private fun calculateSumForDay(transactions: List<Transaction>): Double {
        val today = Calendar.getInstance()

        return transactions.filter { transaction ->
            val transactionDate = Calendar.getInstance().apply { time = transaction.date }

            transactionDate.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                    transactionDate.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
        }.sumOf { it.amount }
    }

    private fun calculateSumForWeek(transactions: List<Transaction>): Double {
        val today = Calendar.getInstance()
        val currentWeek = today.get(Calendar.WEEK_OF_YEAR)
        val currentYear = today.get(Calendar.YEAR)

        return transactions.filter { transaction ->
            val transactionDate = Calendar.getInstance().apply { time = transaction.date }

            transactionDate.get(Calendar.YEAR) == currentYear &&
                    transactionDate.get(Calendar.WEEK_OF_YEAR) == currentWeek
        }.sumOf { it.amount }
    }

    private fun calculateSumForMonth(transactions: List<Transaction>): Double {
        val today = Calendar.getInstance()
        val currentMonth = today.get(Calendar.MONTH)
        val currentYear = today.get(Calendar.YEAR)

        return transactions.filter { transaction ->
            val transactionDate = Calendar.getInstance().apply { time = transaction.date }

            transactionDate.get(Calendar.YEAR) == currentYear &&
                    transactionDate.get(Calendar.MONTH) == currentMonth
        }.sumOf { it.amount }
    }
}

class GeneralViewModelFactory @Inject constructor(
    private val repository: GeneralRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        GeneralViewModel(
            repository
        ) as T
}