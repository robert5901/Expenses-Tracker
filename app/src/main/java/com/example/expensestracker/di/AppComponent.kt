package com.example.expensestracker.di

import android.app.Application
import android.content.Context
import com.example.core_api.annotations.ApplicationScope
import com.example.core_api.mediators.AppProvider
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component
interface AppComponent : AppProvider {

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider {
            return appComponent ?: DaggerAppComponent
                .builder()
                .application(application.applicationContext)
                .build().also { appComponent = it }
        }
    }

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}