package com.example.add_transaction.data.mappers

import com.example.add_transaction.presentation.models.Transaction
import com.example.core_api.entity.ExpenseEntity
import javax.inject.Inject

class TransactionMapper @Inject constructor() {

    fun mapDefaultTransactionToExpenseEntity(
        transaction: Transaction
    ): ExpenseEntity {
        return ExpenseEntity(
            date = transaction.date,
            categoryId = transaction.categoryId,
            amount = transaction.amount,
            currency = transaction.currency,
            comment = transaction.comment
        )
    }
}