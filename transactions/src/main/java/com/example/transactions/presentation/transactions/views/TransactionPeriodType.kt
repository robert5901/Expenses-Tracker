package com.example.transactions.presentation.transactions.views

enum class TransactionPeriodType {
    DAY,
    WEEK,
    MONTH,
    YEAR,
    PERIOD,
    All;

    val dateFormat: String
        get() = when (this) {
            DAY -> "EEE, dd MMMM yyyy"
            WEEK -> "dd MMM"
            MONTH -> "MMMM yyyy"
            YEAR -> "yyyy"
            PERIOD -> "dd MMM yyyy"
            All -> ""
        }
}