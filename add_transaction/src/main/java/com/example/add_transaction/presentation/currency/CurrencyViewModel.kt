package com.example.add_transaction.presentation.currency

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.add_transaction.data.repository.CurrencyRepository
import com.example.add_transaction.presentation.models.Currency
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel(
    private val repository: CurrencyRepository
): ViewModel() {

    private val _currencyList = MutableLiveData<List<Currency>>()
    val currencyList: LiveData<List<Currency>> = _currencyList

    fun getCurrencyList() {
        viewModelScope.launch {
            _currencyList.postValue(repository.getCurrencyList())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CurrencyViewModelFactory @Inject constructor(
    private val repository: CurrencyRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CurrencyViewModel(
            repository
        ) as T
}