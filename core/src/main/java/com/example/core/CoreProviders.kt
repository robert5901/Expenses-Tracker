package com.example.core

import com.example.core_api.database.DatabaseProvider
import com.example.core_api.mediators.AppProvider
import com.example.core_impl.DaggerDatabaseComponent

object CoreProviders {

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }
}