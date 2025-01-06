package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.core_api.entity.ExpenseEntity

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expenseEntity: ExpenseEntity)

    companion object {
        const val TABLE_NAME = "EXPENSES"
    }
}