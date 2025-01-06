package com.example.add_transaction.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class TransactionType : Parcelable {
    EXPENSES, INCOMES
}