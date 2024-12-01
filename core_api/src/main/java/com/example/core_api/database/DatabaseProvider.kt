package com.example.core_api.database

import com.example.core_api.database.dao.ExpensesDao

interface DatabaseProvider {

    fun provideDatabase(): ExpensesDatabaseContract

    fun expensesData(): ExpensesDao
}