package com.example.transactions.presentation.models

import java.util.Date

class Transaction(
    val date: Date,
    val time: Date,
    val categoryId: Long,
    val amount: Double,
    val currency: String,
    val comment: String
)