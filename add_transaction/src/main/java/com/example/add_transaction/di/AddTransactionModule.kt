package com.example.add_transaction.di

import androidx.lifecycle.ViewModelProvider
import com.example.add_transaction.presentation.addTransaction.AddTransactionViewModelFactory
import com.example.add_transaction.presentation.category.CategoryViewModelFactory
import com.example.add_transaction.presentation.currency.CurrencyViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface AddTransactionModule {

    @Binds
    @AddTransactionFactory
    fun bindAddTransactionViewModelFactory(
        addTransactionViewModelFactory: AddTransactionViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @CategoryFactory
    fun bindsCategoryViewModelFactory(
        categoryViewModelFactory: CategoryViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @CurrencyFactory
    fun bindsCurrencyViewModelFactory(
        currencyViewModelFactory: CurrencyViewModelFactory
    ): ViewModelProvider.Factory
}