package com.example.core_api.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "INCOMES",
    foreignKeys = [
        ForeignKey(
            entity = IncomeCategory::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class Income (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Date,
    val category: String,
    val amount: Double,
    val currency: String,
    val comment: String
)