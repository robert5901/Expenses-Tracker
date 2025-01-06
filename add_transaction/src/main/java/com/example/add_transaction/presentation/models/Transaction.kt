package com.example.add_transaction.presentation.models

import java.util.Date

data class Transaction(
    val date: Date,
    val time: Date,
    val categoryId: Long,
    val amount: Double,
    val currency: String,
    val comment: String
)