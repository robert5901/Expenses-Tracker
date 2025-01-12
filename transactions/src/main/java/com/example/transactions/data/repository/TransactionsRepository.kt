package com.example.transactions.data.repository

import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomeCategoryDao
import com.example.core_api.database.dao.IncomesDao
import com.example.transactions.data.mappers.TransactionsMapper
import com.example.transactions.presentation.models.Category
import com.example.transactions.presentation.models.Transaction
import com.example.transactions.presentation.models.TransactionType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionsRepository @Inject constructor(
    private val expenseDao: ExpensesDao,
    private val incomeDao: IncomesDao,
    private val expenseCategoryDao: ExpenseCategoryDao,
    private val incomeCategoryDao: IncomeCategoryDao,
    private val transactionsMapper: TransactionsMapper
) {

    fun getCategoryList(transactionType: TransactionType): Flow<List<Category>> {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                val expenseCategoryEntityListFlow = expenseCategoryDao.getExpenseCategoryList()
                return expenseCategoryEntityListFlow.map {
                    transactionsMapper.mapExpenseCategoryEntityListToCategoryList(it)
                }
            }

            TransactionType.INCOMES -> {
                val incomeCategoryEntityListFlow = incomeCategoryDao.getIncomeCategoryList()
                return incomeCategoryEntityListFlow.map {
                    transactionsMapper.mapIncomeCategoryEntityListToCategoryList(it)
                }
            }
        }
    }

    fun getTransactionList(transactionType: TransactionType): Flow<List<Transaction>> {
        when (transactionType) {
            TransactionType.EXPENSES -> {
                val expenseListFlow = expenseDao.getExpenseList()
                return expenseListFlow.map {
                    transactionsMapper.mapExpenseListToTransactionList(it)
                }
            }

            TransactionType.INCOMES -> {
                val incomeListFlow = incomeDao.getIncomeList()
                return incomeListFlow.map {
                    transactionsMapper.mapIncomeListToTransactionList(it)
                }
            }
        }
    }
}