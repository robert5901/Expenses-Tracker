package com.example.core_api.mediators

interface MediatorsProvider {

    fun provideMainMediator(): MainNavigator

    fun provideGeneralMediator(): GeneralScreenNavigator
}