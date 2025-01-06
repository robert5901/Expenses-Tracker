package com.example.add_transaction.presentation.addTransaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.add_transaction.data.repository.AddTransactionRepository
import com.example.add_transaction.presentation.models.Category
import com.example.add_transaction.presentation.models.TransactionType
import com.example.add_transaction.presentation.models.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class AddTransactionViewModel(
    private val addTransactionRepository: AddTransactionRepository
) : ViewModel() {

    private val _categoryName = MutableLiveData<String>()
    val categoryName: LiveData<String> = _categoryName

    private val transaction = MutableStateFlow(createDefaultTransaction())
    private val scope = CoroutineScope(Dispatchers.IO)
    private var transactionType = TransactionType.EXPENSES

    fun updateCategoryId(category: Category) {
        transaction.update { it.copy(categoryId = category.categoryId) }
        _categoryName.value = category.name
    }

    fun updateDate(date: Date) {
        transaction.update { it.copy(date = date) }
    }

    fun updateTime(time: Date) {
        transaction.update { it.copy(time = time) }
    }

    fun createTransaction(
        transactionType: TransactionType,
        amount: Double,
        currency: String,
        comment: String
    ) {
        transaction.update { it.copy(amount = amount, currency = currency, comment = comment) }

        scope.launch {
            addTransactionRepository.createTransaction(transactionType, transaction.value)
        }
    }

    fun setTransactionType(transactionType: TransactionType) {
        this.transactionType = transactionType
    }

    fun getTransactionType() = transactionType

    private fun createDefaultTransaction() =
        Transaction(
            date = Date(),
            time = Date(),
            categoryId = -1,
            amount = 0.0,
            currency = "RUB",
            comment = ""
        )
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