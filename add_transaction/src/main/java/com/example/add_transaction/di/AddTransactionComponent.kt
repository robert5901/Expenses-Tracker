package com.example.add_transaction.di

import com.example.add_transaction.presentation.AddTransactionFragment
import com.example.core_api.mediators.ProvidersFacade
import dagger.Component

@Component(
    modules = [AddTransactionModule::class],
    dependencies = [ProvidersFacade::class]
)
interface AddTransactionComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): AddTransactionComponent {
            return DaggerAddTransactionComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(addTransactionFragment: AddTransactionFragment)
}