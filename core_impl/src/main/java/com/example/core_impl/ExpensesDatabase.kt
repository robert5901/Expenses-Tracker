package com.example.core_impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core_api.database.ExpensesDatabaseContract
import com.example.core_api.entity.DateConverter
import com.example.core_api.entity.Expense
import com.example.core_api.entity.ExpenseCategoryEntity
import com.example.core_api.entity.Income
import com.example.core_api.entity.IncomeCategoryEntity

@Database(
    entities = [
        Expense::class,
        Income::class,
        ExpenseCategoryEntity::class,
        IncomeCategoryEntity::class
    ],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class ExpensesDatabase : RoomDatabase(), ExpensesDatabaseContract