package com.example.expensestracker.expenses.views

val TransactionPeriodType.isButtonsVisible: Boolean
    get() = when (this) {
        TransactionPeriodType.DAY -> true
        TransactionPeriodType.WEEK -> true
        TransactionPeriodType.MONTH -> true
        TransactionPeriodType.YEAR -> true
        TransactionPeriodType.PERIOD -> false
    }

val TransactionPeriodType.isDateInputLayoutVisible: Boolean
    get() = when (this) {
        TransactionPeriodType.DAY -> true
        TransactionPeriodType.WEEK -> true
        TransactionPeriodType.MONTH -> true
        TransactionPeriodType.YEAR -> false
        TransactionPeriodType.PERIOD -> false
    }

val TransactionPeriodType.isDateTextViewVisible
    get() = when (this) {
        TransactionPeriodType.DAY -> false
        TransactionPeriodType.WEEK -> false
        TransactionPeriodType.MONTH -> false
        TransactionPeriodType.YEAR -> true
        TransactionPeriodType.PERIOD -> false
    }

val TransactionPeriodType.isStartEndDateInputLayoutVisible
    get() = when (this) {
        TransactionPeriodType.DAY -> false
        TransactionPeriodType.WEEK -> false
        TransactionPeriodType.MONTH -> false
        TransactionPeriodType.YEAR -> false
        TransactionPeriodType.PERIOD -> true
    }

val TransactionPeriodType.isHyphenVisible
    get() = when (this) {
        TransactionPeriodType.DAY -> false
        TransactionPeriodType.WEEK -> false
        TransactionPeriodType.MONTH -> false
        TransactionPeriodType.YEAR -> false
        TransactionPeriodType.PERIOD -> true
    }