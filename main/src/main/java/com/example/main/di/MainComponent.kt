package com.example.main.di

import com.example.core_api.mediators.ProvidersFacade
import com.example.main.MainActivity
import dagger.Component

@Component(
    modules = [MainModule::class],
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): MainComponent {
            return DaggerMainComponent.builder().providersFacade(providersFacade).build()
        }
    }

    fun inject(mainActivity: MainActivity)
}