package com.example.transactions.di

import com.example.core_api.mediators.ProvidersFacade
import com.example.transactions.presentation.transactions.TransactionsFragment
import dagger.Component

@Component(
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

    fun inject(transactionsFragment: TransactionsFragment)
}