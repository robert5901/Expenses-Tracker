package com.example.add_transaction.data.repository

import com.example.add_transaction.data.mappers.CategoryMapper
import com.example.add_transaction.presentation.models.Category
import com.example.add_transaction.presentation.models.TransactionType
import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.IncomeCategoryDao
import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.IncomeCategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val expenseCategoryDao: ExpenseCategoryDao,
    private val incomeCategoryDao: IncomeCategoryDao,
    private val categoryMapper: CategoryMapper,
) {

    suspend fun saveCategory(transactionType: TransactionType, categoryName: String) {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                expenseCategoryDao.insert(
                    ExpenseCategoryEntity(
                        name = categoryName
                    )
                )
            }

            TransactionType.INCOMES -> {
                incomeCategoryDao.insert(
                    IncomeCategoryEntity(
                        name = categoryName
                    )
                )
            }
        }
    }

    fun loadCategoryList(transactionType: TransactionType): Flow<List<Category>> {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                val expenseCategoryListEntityFlow = expenseCategoryDao.getExpenseCategoryList()
                return expenseCategoryListEntityFlow.map {
                    categoryMapper.mapExpenseCategoryEntityToCategory(it)
                }
            }

            TransactionType.INCOMES -> {
                val incomeCategoryListEntityFlow = incomeCategoryDao.getIncomeCategoryList()
                return incomeCategoryListEntityFlow.map {
                    categoryMapper.mapIncomeCategoryEntityToCategory(it)
                }
            }
        }
    }

    suspend fun deleteCategory(transactionType: TransactionType, categoryId: Long) {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                expenseCategoryDao.deleteExpenseCategory(categoryId)
            }

            TransactionType.INCOMES -> {
                incomeCategoryDao.deleteIncomeCategory(categoryId)
            }
        }
    }

    suspend fun renameCategory(transactionType: TransactionType, category: Category) {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                expenseCategoryDao.update(categoryMapper.mapToExpenseCategoryEntity(category))
            }

            TransactionType.INCOMES -> {
                incomeCategoryDao.update(categoryMapper.mapToIncomeCategoryEntity(category))
            }
        }
    }
}