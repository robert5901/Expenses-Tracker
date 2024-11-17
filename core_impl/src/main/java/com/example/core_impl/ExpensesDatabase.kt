package com.example.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core_api.database.ExpensesDatabaseContract
import com.example.core_api.dto.DateConverter
import com.example.core_api.dto.Expense
import com.example.core_api.dto.Income

@Database(
    entities = [
        Expense::class,
        Income::class
    ],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class ExpensesDatabase : RoomDatabase(), ExpensesDatabaseContract