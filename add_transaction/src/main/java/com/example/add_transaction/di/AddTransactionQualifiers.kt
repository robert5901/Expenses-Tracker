package com.example.add_transaction.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AddTransactionFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CategoryFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CurrencyFactory