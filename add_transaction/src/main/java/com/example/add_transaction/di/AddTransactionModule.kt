package com.example.add_transaction.di

import androidx.lifecycle.ViewModelProvider
import com.example.add_transaction.navigation.viewModel.AddTransactionViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface AddTransactionModule {

    @Binds
    fun bindAddTransactionViewModelFactory(
        addTransactionViewModelFactory: AddTransactionViewModelFactory
    ): ViewModelProvider.Factory
}