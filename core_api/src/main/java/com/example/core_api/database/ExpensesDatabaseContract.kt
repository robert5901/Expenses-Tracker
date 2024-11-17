package com.example.core_api.database

interface ExpensesDatabaseContract {

    fun expensesDao(): ExpensesDao
}