package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.core_api.dto.ExpenseCategory

@Dao
interface ExpenseCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expenseCategory: ExpenseCategory)

    @Update
    suspend fun update(expenseCategory: ExpenseCategory)

    @Delete
    suspend fun delete(expenseCategory: ExpenseCategory)

    companion object {
        const val TABLE_NAME = "EXPENSE_CATEGORY"
    }
}