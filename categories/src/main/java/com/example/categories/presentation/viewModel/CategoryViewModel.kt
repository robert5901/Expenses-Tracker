package com.example.categories.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.categories.data.repository.CategoryRepository
import com.example.categories.presentation.models.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    private val _expenseCategoryList = MutableStateFlow<List<Category>>(emptyList())
    val expenseCategoryList: LiveData<List<Category>> = _expenseCategoryList.asLiveData()

    private val _incomeCategoryList = MutableStateFlow<List<Category>>(emptyList())
    val incomeCategoryList: LiveData<List<Category>> = _incomeCategoryList.asLiveData()

    fun saveExpenseCategory(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.saveExpenseCategory(categoryName)
        }
    }

    fun saveIncomeCategory(categoryName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.saveIncomeCategory(categoryName)
        }
    }

    fun loadExpenseCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.loadExpenseCategoryList()
                .collect { categories ->
                    _expenseCategoryList.value = categories
                }
        }
    }

    fun loadIncomeCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.loadIncomeCategoryList()
                .collect { categories ->
                    _incomeCategoryList.value = categories
                }
        }
    }

    fun deleteExpenseCategory(categoryId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.deleteExpenseCategory(categoryId)
        }
    }

    fun deleteIncomeCategory(categoryId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.deleteIncomeCategory(categoryId)
        }
    }

    fun renameExpenseCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.renameExpenseCategory(category)
        }
    }

    fun renameIncomeCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryRepository.renameIncomeCategory(category)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory @Inject constructor(
    private val categoryRepository: CategoryRepository,
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(
            categoryRepository
        ) as T
    }
}