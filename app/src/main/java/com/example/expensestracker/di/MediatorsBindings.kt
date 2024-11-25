package com.example.expensestracker.di

import com.example.add_transaction.navigation.AddTransactionScreenNavigatorImpl
import com.example.core_api.mediators.AddTransactionScreenNavigator
import com.example.core_api.mediators.GeneralScreenNavigator
import com.example.general.navigation.GeneralScreenNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorsBindings {

    @Binds
    @Reusable
    fun bindsGeneralMediator(
        generalMediatorImpl: GeneralScreenNavigatorImpl
    ): GeneralScreenNavigator

    @Binds
    @Reusable
    fun bindsAddTransactionMediator(
        addTransactionMediatorImpl: AddTransactionScreenNavigatorImpl
    ): AddTransactionScreenNavigator
}