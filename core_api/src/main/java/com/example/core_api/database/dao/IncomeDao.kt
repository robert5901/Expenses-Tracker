package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.core_api.entity.IncomeEntity

@Dao
interface IncomesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(incomeEntity: IncomeEntity)

    companion object {
        const val TABLE_NAME = "INCOMES"
    }
}