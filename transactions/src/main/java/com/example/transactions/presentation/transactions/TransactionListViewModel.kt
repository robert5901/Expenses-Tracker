package com.example.transactions.presentation.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.transactions.data.repository.TransactionsRepository
import com.example.transactions.presentation.models.Category
import com.example.transactions.presentation.models.Transaction
import com.example.transactions.presentation.models.TransactionCategory
import com.example.transactions.presentation.models.TransactionType
import com.example.transactions.presentation.transactions.views.TransactionPeriodType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class TransactionListViewModel(
    private val transactionsRepository: TransactionsRepository
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var transactionType = TransactionType.EXPENSES

    private val _categoryList = MutableStateFlow<List<Category>>(emptyList())
    private val _transactionList = MutableStateFlow<List<Transaction>>(emptyList())
    private val _filteredTransactionList = MutableStateFlow<List<Transaction>>(emptyList())

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: LiveData<Double> = _totalAmount.asLiveData()

    private var startDateForTypePeriod = Date()
    private var endDateForTypePeriod = Date()

    val transactionCategoryList: LiveData<List<TransactionCategory>> =
        combine(_categoryList, _filteredTransactionList) { categories, transactions ->
            val transactionCategories = categories.mapNotNull { category ->
                val totalAmount = transactions
                    .filter { it.categoryId == category.categoryId }
                    .sumOf { it.amount }

                if (totalAmount > 0.0) {
                    // Только категории, где сумма транзакций больше 0
                    TransactionCategory(
                        title = category.name,
                        amount = totalAmount
                    )
                } else {
                    null
                }
            }.sortedByDescending { it.amount }

            _totalAmount.value = transactionCategories.sumOf { it.amount }

            transactionCategories
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList()).asLiveData()

    fun setTransactionType(transactionType: TransactionType) {
        this.transactionType = transactionType
    }

    fun getTransactionType() = transactionType

    fun loadTransactionCategoryList(transactionPeriodType: TransactionPeriodType) {
        scope.launch {
            transactionsRepository.getCategoryList(transactionType)
                .collect { categories ->
                    _categoryList.value = categories
                }
        }

        scope.launch {
            transactionsRepository.getTransactionList(transactionType)
                .collect { transactions ->
                    _transactionList.value = transactions
                    filterTransactionCategoryByPeriod(transactionPeriodType)
                }
        }
    }

    fun filterTransactionCategoryByPeriod(
        transactionPeriodType: TransactionPeriodType,
        date: Date = Date()
    ) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val periodStart: Date
        val periodEnd: Date

        when (transactionPeriodType) {
            TransactionPeriodType.DAY -> {
                // Период "день" - фильтруем транзакции, произошедшие в этот день
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                periodStart = calendar.time

                calendar.add(Calendar.DAY_OF_MONTH, 1)
                periodEnd = calendar.time
            }

            TransactionPeriodType.WEEK -> {
                calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                periodStart = calendar.time

                calendar.add(Calendar.WEEK_OF_YEAR, 1)
                periodEnd = calendar.time
            }

            TransactionPeriodType.MONTH -> {
                calendar.set(Calendar.DAY_OF_MONTH, 1)
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                periodStart = calendar.time

                calendar.add(Calendar.MONTH, 1)
                periodEnd = calendar.time
            }

            TransactionPeriodType.YEAR -> {
                calendar.set(Calendar.DAY_OF_YEAR, 1)
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                periodStart = calendar.time

                calendar.add(Calendar.YEAR, 1)
                periodEnd = calendar.time
            }

            TransactionPeriodType.PERIOD -> {
                // Определяем, что именно изменилось (начальная или конечная дата)
                if (date.before(endDateForTypePeriod)) {
                    startDateForTypePeriod = date
                } else {
                    endDateForTypePeriod = date
                }

                // Установка времени начальной даты на 00:00:00:000
                periodStart = Calendar.getInstance().apply {
                    time = startDateForTypePeriod
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }.time

                // Установка времени конечной даты на 23:59:59:999
                periodEnd = Calendar.getInstance().apply {
                    time = endDateForTypePeriod
                    set(Calendar.HOUR_OF_DAY, 23)
                    set(Calendar.MINUTE, 59)
                    set(Calendar.SECOND, 59)
                    set(Calendar.MILLISECOND, 999)
                }.time
            }

            else -> {
                periodStart = Date(0)
                periodEnd = Date(Long.MAX_VALUE)
            }
        }

        val filteredTransactions = _transactionList.value.filter { transaction ->
            transaction.date in periodStart..periodEnd
        }

        _filteredTransactionList.value = filteredTransactions
    }
}

@Suppress("UNCHECKED_CAST")
class TransactionListViewModelFactory @Inject constructor(
    private val transactionsRepository: TransactionsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        TransactionListViewModel(
            transactionsRepository
        ) as T
}