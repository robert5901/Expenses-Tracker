package com.example.core_api.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert() {
    }

    companion object {
        const val TABLE_NAME = "EXPENSES"
    }
}