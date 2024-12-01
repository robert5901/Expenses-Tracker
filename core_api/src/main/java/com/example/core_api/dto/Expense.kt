package com.example.core_api.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.core_api.database.dao.ExpensesDao
import java.util.Date

@Entity(
    tableName = ExpensesDao.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ExpenseCategory::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo
    val date: Date,
    val category: String,
    val amount: Double,
    val currency: String,
    val comment: String
)