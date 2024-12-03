package com.example.core_api.mediators

interface MediatorsProvider {

    fun provideGeneralMediator(): GeneralScreenNavigator

    fun provideAddTransactionMediator(): AddTransactionScreenNavigator

    fun provideTransactionsMediator(): TransactionsScreenNavigator

    fun provideCategoriesMediator(): CategoryScreenNavigator
}