package com.example.transactions.di

import com.example.core_api.mediators.ProvidersFacade
import com.example.transactions.presentation.TransactionListMainFragment
import dagger.Component

@Component(
    modules = [TransactionsModule::class],
    dependencies = [ProvidersFacade::class]
)
interface TransactionsComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): TransactionsComponent {
            return DaggerTransactionsComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(transactionListMainFragment: TransactionListMainFragment)
}