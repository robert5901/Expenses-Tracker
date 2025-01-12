package com.example.add_transaction.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.add_transaction.data.repository.CategoryRepository
import com.example.add_transaction.presentation.models.Category
import com.example.add_transaction.presentation.models.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    private val _categoryList = MutableStateFlow<List<Category>>(emptyList())
    val categoryList: LiveData<List<Category>> = _categoryList.asLiveData()

    private val scope = CoroutineScope(Dispatchers.IO)

    fun saveCategory(transactionType: TransactionType, categoryName: String) {
        scope.launch {
            categoryRepository.saveCategory(transactionType, categoryName)
        }
    }

    fun loadCategoryList(transactionType: TransactionType) {
        viewModelScope.launch {
            categoryRepository.loadCategoryList(transactionType)
                .collect { categories ->
                    _categoryList.value = categories
                }
        }
    }

    fun deleteCategory(transactionType: TransactionType, categoryId: Long) {
        scope.launch {
            categoryRepository.deleteCategory(transactionType, categoryId)
        }
    }

    fun renameCategory(transactionType: TransactionType, newCategory: Category) {
        scope.launch {
            categoryRepository.renameCategory(transactionType, newCategory)
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