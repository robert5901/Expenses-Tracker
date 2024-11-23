package com.example.expensestracker.di

import com.example.core_api.mediators.GeneralScreenNavigator
import com.example.core_api.mediators.MainNavigator
import com.example.general.navigation.GeneralScreenNavigatorImpl
import com.example.main.navigation.MainNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorsBindings {

    @Binds
    @Reusable
    fun bindsMainMediator(mainMediatorImpl: MainNavigatorImpl): MainNavigator

    @Binds
    @Reusable
    fun bindsGeneralMediator(generalMediatorImpl: GeneralScreenNavigatorImpl): GeneralScreenNavigator
}