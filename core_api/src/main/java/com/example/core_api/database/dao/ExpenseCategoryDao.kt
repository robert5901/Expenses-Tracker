package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Delete
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

    @Delete
    suspend fun delete(expenseCategory: ExpenseCategoryEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE categoryId = :categoryId")
    suspend fun deleteExpenseCategory(categoryId: Long)

    @Query("SELECT * FROM $TABLE_NAME WHERE categoryId != $DEFAULT_CATEGORY_ID")
    fun loadExpenseCategoryList(): Flow<List<ExpenseCategoryEntity>>

    @Query("SELECT COUNT(*) FROM $TABLE_NAME WHERE categoryId = :categoryId")
    suspend fun isCategoryExists(categoryId: Long): Int

    companion object {
        const val TABLE_NAME = "EXPENSE_CATEGORY"
        const val DEFAULT_CATEGORY_ID = -1
    }
}