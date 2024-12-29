package com.example.categories.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CategoryType: Parcelable {
    EXPENSES, INCOMES;
}