package com.example.core_api.database

import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.dao.IncomeCategoryDao
import com.example.core_api.database.dao.IncomesDao

interface ExpensesDatabaseContract {

    fun expensesDao(): ExpensesDao

    fun incomesDao(): IncomesDao

    fun expenseCategoryDao(): ExpenseCategoryDao

    fun incomeCategoryDao(): IncomeCategoryDao
}