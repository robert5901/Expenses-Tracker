package com.example.transactions.data.mappers

import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.ExpenseEntity
import com.example.core_api.entity.IncomeCategoryEntity
import com.example.core_api.entity.IncomeEntity
import com.example.transactions.presentation.models.Category
import com.example.transactions.presentation.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionsMapper @Inject constructor() {

    fun mapExpenseCategoryEntityListToCategoryList(
        expenseCategoryList: Flow<List<ExpenseCategoryEntity>>
    ): Flow<List<Category>> =
        expenseCategoryList.map { entityList ->
            entityList.map { expenseCategoryEntity ->
                Category(
                    categoryId = expenseCategoryEntity.categoryId,
                    name = expenseCategoryEntity.name
                )
            }
        }

    fun mapIncomeCategoryEntityListToCategoryList(
        incomeCategoryList: Flow<List<IncomeCategoryEntity>>
    ): Flow<List<Category>> =
        incomeCategoryList.map { entityList ->
            entityList.map { incomeCategoryEntity ->
                Category(
                    categoryId = incomeCategoryEntity.categoryId,
                    name = incomeCategoryEntity.name
                )
            }
        }

    fun mapExpenseListToTransactionList(
        expenseList: Flow<List<ExpenseEntity>>
    ): Flow<List<Transaction>> =
        expenseList.map { expenses ->
            expenses.map { expense ->
                Transaction(
                    date = expense.date,
                    time = expense.time,
                    categoryId = expense.categoryId,
                    amount = expense.amount,
                    currency = expense.currency,
                    comment = expense.comment
                )
            }
        }

    fun mapIncomeListToTransactionList(
        incomeList: Flow<List<IncomeEntity>>
    ): Flow<List<Transaction>> =
        incomeList.map { incomes ->
            incomes.map { income ->
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
}