package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_api.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expenseEntity: ExpenseEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getExpenseList(): Flow<List<ExpenseEntity>>

    companion object {
        const val TABLE_NAME = "EXPENSES"
    }
}