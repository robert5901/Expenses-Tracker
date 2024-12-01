package com.example.core_api.database

import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.ExpensesDao

interface ExpensesDatabaseContract {

    fun expensesDao(): ExpensesDao

    fun expenseCategoryDao(): ExpenseCategoryDao
}