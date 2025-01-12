package com.example.general.data.mappers

import com.example.core_api.entity.ExpenseEntity
import com.example.core_api.entity.IncomeEntity
import com.example.general.presentation.models.Transaction
import javax.inject.Inject

class GeneralTransactionMapper @Inject constructor() {

    fun mapExpenseEntityListToTransactionList(
        expenseEntityList: List<ExpenseEntity>
    ): List<Transaction> =
        expenseEntityList.map { expense ->
            Transaction(
                date = expense.date,
                amount = expense.amount
            )
        }

    fun mapIncomeEntityListToTransactionList(
        incomeEntityList: List<IncomeEntity>
    ):List<Transaction> =
        incomeEntityList.map { income ->
            Transaction(
                date = income.date,
                amount = income.amount
            )
        }
}