package com.example.expensestracker.expenses.views

enum class TransactionPeriodType {
    DAY,
    WEEK,
    MONTH,
    YEAR,
    PERIOD;

    val dateFormat: String
        get() = when (this) {
            DAY -> "EEE, dd MMMM yyyy"
            WEEK -> "dd MMM"
            MONTH -> "MMMM yyyy"
            YEAR -> "yyyy"
            PERIOD -> "dd MMM yyyy"
        }
}