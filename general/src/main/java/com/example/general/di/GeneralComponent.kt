package com.example.general.di

import com.example.core_api.mediators.ProvidersFacade
import com.example.general.presentation.GeneralFragment
import dagger.Component

@Component(
    modules = [GeneralModule::class],
    dependencies = [ProvidersFacade::class]
)
interface GeneralComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): GeneralComponent {
            return DaggerGeneralComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(generalFragment: GeneralFragment)
}