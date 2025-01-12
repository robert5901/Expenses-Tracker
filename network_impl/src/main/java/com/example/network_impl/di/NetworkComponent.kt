package com.example.network_impl.di

import com.example.core_api.annotations.ApplicationScope
import com.example.core_api.network.NetworkProvider
import dagger.Component

@Component(
    modules = [NetworkModule::class]
)
@ApplicationScope
interface NetworkComponent: NetworkProvider