package com.example.general.di

import androidx.lifecycle.ViewModelProvider
import com.example.general.presentation.GeneralViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface GeneralModule {

    @Binds
    fun bindGeneralViewModelFactory(
        generalViewModelFactory: GeneralViewModelFactory
    ): ViewModelProvider.Factory
}