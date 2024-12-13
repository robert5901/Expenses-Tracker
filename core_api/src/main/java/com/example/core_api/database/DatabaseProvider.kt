package com.example.core_api.database

import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomeCategoryDao

interface DatabaseProvider {

    fun provideDatabase(): ExpensesDatabaseContract

    fun expensesDao(): ExpensesDao

    fun expenseCategoryDao(): ExpenseCategoryDao

    fun incomeCategoryDao(): IncomeCategoryDao
}