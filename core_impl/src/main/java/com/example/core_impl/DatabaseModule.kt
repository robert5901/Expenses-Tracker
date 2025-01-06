package com.example.core_impl

import android.content.Context
import androidx.room.Room
import com.example.core_api.database.dao.ExpensesDao
import com.example.core_api.database.ExpensesDatabaseContract
import com.example.core_api.database.dao.ExpenseCategoryDao
import com.example.core_api.database.dao.IncomeCategoryDao
import com.example.core_api.database.dao.IncomesDao
import dagger.Module
import dagger.Provides
import dagger.Reusable

private const val EXPENSES_DATABASE_NAME = "EXPENSES_DB"

@Module
class DatabaseModule {

    @Provides
    @Reusable
    fun provideExpensesDao(expensesDatabaseContract: ExpensesDatabaseContract): ExpensesDao {
        return expensesDatabaseContract.expensesDao()
    }

    @Provides
    @Reusable
    fun provideIncomesDao(expensesDatabaseContract: ExpensesDatabaseContract): IncomesDao {
        return expensesDatabaseContract.incomesDao()
    }

    @Provides
    @Reusable
    fun provideExpenseCategoryDao(
        expensesDatabaseContract: ExpensesDatabaseContract
    ): ExpenseCategoryDao {
        return expensesDatabaseContract.expenseCategoryDao()
    }

    @Provides
    @Reusable
    fun provideIncomeCategoryDao(
        expensesDatabaseContract: ExpensesDatabaseContract
    ): IncomeCategoryDao {
        return expensesDatabaseContract.incomeCategoryDao()
    }

    @Provides
    @Reusable
    fun provideDatabase(context: Context): ExpensesDatabaseContract {
        return Room.databaseBuilder(
            context,
            ExpensesDatabase::class.java, EXPENSES_DATABASE_NAME
        ).build()
    }
}