package com.example.transactions.data.mappers

import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.ExpenseEntity
import com.example.core_api.entity.IncomeCategoryEntity
import com.example.core_api.entity.IncomeEntity
import com.example.transactions.presentation.models.Category
import com.example.transactions.presentation.models.Transaction
import javax.inject.Inject

class TransactionsMapper @Inject constructor() {

    fun mapExpenseCategoryEntityListToCategoryList(
        expenseCategoryList: List<ExpenseCategoryEntity>
    ): List<Category> =
        expenseCategoryList.map {
            Category(
                categoryId = it.categoryId,
                name = it.name
            )
        }

    fun mapIncomeCategoryEntityListToCategoryList(
        incomeCategoryList: List<IncomeCategoryEntity>
    ): List<Category> =
        incomeCategoryList.map {
            Category(
                categoryId = it.categoryId,
                name = it.name
            )
        }

    fun mapExpenseListToTransactionList(
        expenseList: List<ExpenseEntity>
    ): List<Transaction> =
        expenseList.map { expense ->
            Transaction(
                date = expense.date,
                time = expense.time,
                categoryId = expense.categoryId,
                amount = expense.amount,
                currency = expense.currency,
                comment = expense.comment
            )
        }

    fun mapIncomeListToTransactionList(
        incomeList: List<IncomeEntity>
    ): List<Transaction> =
        incomeList.map { income ->
            Transaction(
                date = income.date,
                time = income.time,
                categoryId = income.categoryId,
                amount = income.amount,
                currency = income.currency,
                comment = income.comment
            )
        }
}