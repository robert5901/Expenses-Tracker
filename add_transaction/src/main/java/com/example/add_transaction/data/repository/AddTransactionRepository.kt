package com.example.add_transaction.data.repository

import com.example.add_transaction.data.mappers.TransactionMapper
import com.example.add_transaction.presentation.models.Transaction
import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.entity.ExpenseCategoryEntity
import javax.inject.Inject

class AddTransactionRepository @Inject constructor(
    private val expenseDao: ExpensesDao,
//    private val incomeDao: IncomeDao
    private val expenseCategoryDao: ExpenseCategoryDao,
//    private val incomeCategoryDao: IncomeCategoryDao,
    private val transactionMapper: TransactionMapper
) {

    suspend fun createDefaultExpense(transaction: Transaction) {
        // Create default category if doesn't exist.
        createExpenseDefaultCategoryIfNotExist()
        expenseDao.insert(transactionMapper.mapDefaultTransactionToExpenseEntity(transaction))
    }

    suspend fun createDefaultIncome(transaction: Transaction) {

    }

    suspend fun getDefaultExpenseId() = expenseDao.getDefaultExpenseId()

    private suspend fun createExpenseDefaultCategoryIfNotExist() {
        val defaultCategoryId = -1L
        val exists = expenseCategoryDao.isCategoryExists(defaultCategoryId)
        if (exists == 0) {
            val defaultCategory = ExpenseCategoryEntity(
                categoryId = defaultCategoryId,
                name = "Default Category"
            )
            expenseCategoryDao.insert(defaultCategory)
        }
    }
}