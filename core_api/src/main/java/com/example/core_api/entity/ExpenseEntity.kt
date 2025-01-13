package com.example.core_api.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.core_api.database.dao.ExpensesDao
import java.util.Date

@Entity(
    tableName = ExpensesDao.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ExpenseCategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["categoryId"])]
)
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val date: Date,
    val time: Date,
    val categoryId: Long,
    val amount: Double,
    val currency: String,
    val comment: String
)