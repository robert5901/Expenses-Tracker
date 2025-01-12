package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_api.entity.IncomeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(incomeEntity: IncomeEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getIncomeList(): Flow<List<IncomeEntity>>

    companion object {
        const val TABLE_NAME = "INCOMES"
    }
}