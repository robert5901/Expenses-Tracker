package com.example.expensestracker.di

import android.app.Application
import com.example.core.CoreProviders
import com.example.core_api.database.DatabaseProvider
import com.example.core_api.mediators.AppProvider
import com.example.core_api.mediators.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
    modules = [MediatorsBindings::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application): FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .databaseProvider(
                    CoreProviders.createDatabaseBuilder(
                        AppComponent.create(application)
                    )
                ).build()
    }
}