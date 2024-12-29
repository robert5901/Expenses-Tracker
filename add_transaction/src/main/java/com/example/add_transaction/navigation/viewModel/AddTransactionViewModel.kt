package com.example.add_transaction.navigation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.add_transaction.data.repository.AddTransactionRepository
import com.example.add_transaction.presentation.models.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTransactionViewModel(
    private val addTransactionRepository: AddTransactionRepository
) : ViewModel() {

    private val _transaction = MutableStateFlow<Transaction?>(null)
    val transaction = _transaction.asStateFlow()

    private val _defaultTransactionId = MutableLiveData<Long>()
    val defaultTransactionId: LiveData<Long> = _defaultTransactionId

    private val scope = CoroutineScope(Dispatchers.IO)

    fun createDefaultExpense(transaction: Transaction) {
        scope.launch {
            addTransactionRepository.createDefaultExpense(transaction)
        }
    }

    fun createDefaultIncome(transaction: Transaction) {
        scope.launch {
            addTransactionRepository.createDefaultIncome(transaction)
        }
    }

    fun getDefaultExpenseId() {
        scope.launch {
            _defaultTransactionId.postValue(addTransactionRepository.getDefaultExpenseId())
        }
    }

    fun getIncomeExpenseId() {}
}

@Suppress("UNCHECKED_CAST")
class AddTransactionViewModelFactory @Inject constructor(
    private val addTransactionRepository: AddTransactionRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        AddTransactionViewModel(
            addTransactionRepository
        ) as T
}