package com.example.transactions.presentation.transactions.views

val TransactionPeriodType.isButtonsVisible: Boolean
    get() = when (this) {
        TransactionPeriodType.DAY -> true
        TransactionPeriodType.WEEK -> true
        TransactionPeriodType.MONTH -> true
        TransactionPeriodType.YEAR -> true
        TransactionPeriodType.PERIOD -> false
        TransactionPeriodType.All -> false
    }

val TransactionPeriodType.isDateInputLayoutVisible: Boolean
    get() = when (this) {
        TransactionPeriodType.DAY -> true
        TransactionPeriodType.WEEK -> true
        TransactionPeriodType.MONTH -> true
        TransactionPeriodType.YEAR -> false
        TransactionPeriodType.PERIOD -> false
        TransactionPeriodType.All -> false
    }

val TransactionPeriodType.isDateTextViewVisible
    get() = when (this) {
        TransactionPeriodType.DAY -> false
        TransactionPeriodType.WEEK -> false
        TransactionPeriodType.MONTH -> false
        TransactionPeriodType.YEAR -> true
        TransactionPeriodType.PERIOD -> false
        TransactionPeriodType.All -> false
    }

val TransactionPeriodType.isStartEndDateInputLayoutVisible
    get() = when (this) {
        TransactionPeriodType.DAY -> false
        TransactionPeriodType.WEEK -> false
        TransactionPeriodType.MONTH -> false
        TransactionPeriodType.YEAR -> false
        TransactionPeriodType.PERIOD -> true
        TransactionPeriodType.All -> false
    }

val TransactionPeriodType.isHyphenVisible
    get() = when (this) {
        TransactionPeriodType.DAY -> false
        TransactionPeriodType.WEEK -> false
        TransactionPeriodType.MONTH -> false
        TransactionPeriodType.YEAR -> false
        TransactionPeriodType.PERIOD -> true
        TransactionPeriodType.All -> false
    }