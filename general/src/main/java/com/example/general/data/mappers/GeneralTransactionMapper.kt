package com.example.general.data.mappers

import com.example.core_api.entity.ExpenseEntity
import com.example.core_api.entity.IncomeEntity
import com.example.general.presentation.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeneralTransactionMapper @Inject constructor() {

    fun mapExpenseEntityListToTransactionList(
        expenseEntityList: Flow<List<ExpenseEntity>>
    ): Flow<List<Transaction>> =
        expenseEntityList.map { expensesEntity ->
            expensesEntity.map { expense ->
                Transaction(
                    date = expense.date,
                    amount = expense.amount
                )
            }
        }

    fun mapIncomeEntityListToTransactionList(
        incomeEntityList: Flow<List<IncomeEntity>>
    ): Flow<List<Transaction>> =
        incomeEntityList.map { incomesEntity ->
            incomesEntity.map { income ->
                Transaction(
                    date = income.date,
                    amount = income.amount
                )
            }
        }
}