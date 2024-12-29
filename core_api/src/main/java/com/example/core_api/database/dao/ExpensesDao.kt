package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_api.entity.ExpenseEntity

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(expenseEntity: ExpenseEntity)

    @Query("SELECT id FROM $TABLE_NAME WHERE categoryId = -1 LIMIT 1")
    suspend fun getDefaultExpenseId() : Long

    @Query("UPDATE $TABLE_NAME SET categoryId = :categoryId WHERE id = :transactionId")
    suspend fun updateExpenseCategoryId(transactionId: Long, categoryId: Long)

    companion object {
        const val TABLE_NAME = "EXPENSES"
    }
}