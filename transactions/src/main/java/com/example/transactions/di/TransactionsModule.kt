package com.example.transactions.di

import androidx.lifecycle.ViewModelProvider
import com.example.transactions.presentation.transactions.TransactionListViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface TransactionsModule {

    @Binds
    fun bindTransactionListViewModelFactory(
        transactionListViewModelFactory: TransactionListViewModelFactory
    ): ViewModelProvider.Factory
}