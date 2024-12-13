package com.example.categories.data.repository

import com.example.categories.data.mappers.CategoryMapper
import com.example.categories.presentation.models.Category
import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.IncomeCategoryDao
import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.IncomeCategoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val expenseCategoryDao: ExpenseCategoryDao,
    private val incomeCategoryDao: IncomeCategoryDao,
    private val categoryMapper: CategoryMapper
) {

    suspend fun saveExpenseCategory(categoryName: String) {
        expenseCategoryDao.insert(
            ExpenseCategoryEntity(
                name = categoryName
            )
        )
    }

    suspend fun saveIncomeCategory(categoryName: String) {
        incomeCategoryDao.insert(
            IncomeCategoryEntity(
                name = categoryName
            )
        )
    }

    fun loadExpenseCategoryList(): Flow<List<Category>> {
        val expenseCategoryListEntity = expenseCategoryDao.loadExpenseCategoryList()
        return categoryMapper.mapExpenseCategoryEntityToCategory(expenseCategoryListEntity)
    }

    fun loadIncomeCategoryList(): Flow<List<Category>> {
        val incomeCategoryListEntity = incomeCategoryDao.loadIncomeCategoryList()
        return categoryMapper.mapIncomeCategoryEntityToCategory(incomeCategoryListEntity)
    }

    suspend fun deleteExpenseCategory(categoryId: Long) {
        expenseCategoryDao.deleteExpenseCategory(categoryId)
    }

    suspend fun deleteIncomeCategory(categoryId: Long) {
        incomeCategoryDao.deleteIncomeCategory(categoryId)
    }

    suspend fun renameExpenseCategory(category: Category) {
        expenseCategoryDao.update(categoryMapper.mapToExpenseCategoryEntity(category))
    }

    suspend fun renameIncomeCategory(category: Category) {
        incomeCategoryDao.update(categoryMapper.mapToIncomeCategoryEntity(category))
    }
}