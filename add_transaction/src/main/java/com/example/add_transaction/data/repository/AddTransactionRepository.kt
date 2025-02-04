package com.example.add_transaction.data.repository

import com.example.add_transaction.data.mappers.TransactionMapper
import com.example.add_transaction.presentation.models.Transaction
import com.example.add_transaction.presentation.models.TransactionType
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomesDao
import javax.inject.Inject

class AddTransactionRepository @Inject constructor(
    private val expenseDao: ExpensesDao,
    private val incomeDao: IncomesDao,
    private val transactionMapper: TransactionMapper
) {

    suspend fun createTransaction(transactionType: TransactionType, transaction: Transaction) {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                expenseDao.insert(transactionMapper.mapTransactionToExpenseEntity(transaction))
            }

            TransactionType.INCOMES -> {
                incomeDao.insert(transactionMapper.mapTransactionToIncomeEntity(transaction))
            }
        }
    }
}