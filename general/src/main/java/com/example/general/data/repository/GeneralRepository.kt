package com.example.general.data.repository

import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomesDao
import com.example.general.data.mappers.GeneralTransactionMapper
import com.example.general.presentation.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeneralRepository @Inject constructor(
    private val expenseDao: ExpensesDao,
    private val incomeDao: IncomesDao,
    private val generalTransactionMapper: GeneralTransactionMapper
) {

    fun getExpenses(): Flow<List<Transaction>> {
        val expenseListFlow = expenseDao.getExpenseList()
        return expenseListFlow.map {
            generalTransactionMapper.mapExpenseEntityListToTransactionList(it)
        }
    }

    fun getIncomes(): Flow<List<Transaction>> {
        val incomeListFlow = incomeDao.getIncomeList()
        return incomeListFlow.map {
            generalTransactionMapper.mapIncomeEntityListToTransactionList(it)
        }
    }
}