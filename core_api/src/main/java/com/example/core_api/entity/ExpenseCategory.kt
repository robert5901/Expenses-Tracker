package com.example.core_api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core_api.database.dao.ExpenseCategoryDao

@Entity(tableName = ExpenseCategoryDao.TABLE_NAME)
data class ExpenseCategory(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long,
    val name: String
)