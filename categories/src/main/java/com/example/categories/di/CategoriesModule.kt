package com.example.categories.di

import androidx.lifecycle.ViewModelProvider
import com.example.categories.presentation.viewModel.CategoryViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface CategoriesModule {

    @Binds
    fun bindsCategoryViewModelFactory(
        categoryViewModelFactory: CategoryViewModelFactory
    ): ViewModelProvider.Factory
}