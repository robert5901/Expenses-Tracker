package com.example.expensestracker.categoryTransactions.models

import java.util.Date

data class CategoryTransaction(
    val id: String,
    val date: Date,
    val amount: Int,
    val currency: String, // TODO Currency model
    val comment: String
)