package com.example.categories.di

import com.example.categories.presentation.CategoryFragment
import com.example.core_api.mediators.ProvidersFacade
import dagger.Component

@Component(
    modules = [CategoriesModule::class],
    dependencies = [ProvidersFacade::class]
)
interface CategoriesComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): CategoriesComponent {
            return DaggerCategoriesComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(categoryFragment: CategoryFragment)
}