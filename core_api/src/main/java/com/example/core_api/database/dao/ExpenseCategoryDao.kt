package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core_api.entity.ExpenseCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expenseCategory: ExpenseCategoryEntity)

    @Update
    suspend fun update(expenseCategory: ExpenseCategoryEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE categoryId = :categoryId")
    suspend fun deleteExpenseCategory(categoryId: Long)

    @Query("SELECT * FROM $TABLE_NAME")
    fun loadExpenseCategoryList(): Flow<List<ExpenseCategoryEntity>>

    companion object {
        const val TABLE_NAME = "EXPENSE_CATEGORY"
    }
}