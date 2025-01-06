package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core_api.entity.IncomeCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(incomeCategory: IncomeCategoryEntity)

    @Update
    suspend fun update(incomeCategory: IncomeCategoryEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE categoryId = :categoryId")
    suspend fun deleteIncomeCategory(categoryId: Long)

    @Query("SELECT * FROM $TABLE_NAME")
    fun loadIncomeCategoryList(): Flow<List<IncomeCategoryEntity>>

    companion object {
        const val TABLE_NAME = "INCOME_CATEGORY"
    }
}