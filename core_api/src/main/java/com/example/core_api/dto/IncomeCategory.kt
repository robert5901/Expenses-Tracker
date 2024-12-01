package com.example.core_api.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "INCOME_CATEGORY")
data class IncomeCategory(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long,
    val name: String
)
