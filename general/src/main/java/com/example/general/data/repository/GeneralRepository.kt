package com.example.general.data.repository

import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomesDao
import com.example.general.data.mappers.GeneralTransactionMapper
import com.example.general.presentation.models.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeneralRepository @Inject constructor(
    private val expenseDao: ExpensesDao,
    private val incomeDao: IncomesDao,
    private val generalTransactionMapper: GeneralTransactionMapper
) {

    fun getExpenses(): Flow<List<Transaction>> {
        val expenseList = expenseDao.getExpenseList()
        return generalTransactionMapper.mapExpenseEntityListToTransactionList(expenseList)
    }

    fun getIncomes(): Flow<List<Transaction>> {
        val incomeList = incomeDao.getIncomeList()
        return generalTransactionMapper.mapIncomeEntityListToTransactionList(incomeList)
    }
}