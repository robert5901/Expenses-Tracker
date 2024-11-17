package com.example.expensestracker.app

import android.app.Application
import com.example.core_api.mediators.ExpensesTrackerApp
import com.example.core_api.mediators.ProvidersFacade
import com.example.expensestracker.di.FacadeComponent

class App : Application(), ExpensesTrackerApp {

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun onCreate() {
        super.onCreate()

        getFacade()
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }
}