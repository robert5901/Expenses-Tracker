package com.example.core_api.database

interface DatabaseProvider {

    fun provideDatabase(): ExpensesDatabaseContract

    fun expensesData(): ExpensesDao
}