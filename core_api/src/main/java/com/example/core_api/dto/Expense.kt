package com.example.core_api.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "EXPENSES")
data class Expense(
    @PrimaryKey
    val id: Long,
    val date: Date,
    val category: String,
    val amount: Double,
    val currency: String,
    val comment: String
)