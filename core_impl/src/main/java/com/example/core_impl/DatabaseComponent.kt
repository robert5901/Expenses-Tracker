package com.example.core_impl

import com.example.core_api.annotations.ApplicationScope
import com.example.core_api.database.DatabaseProvider
import com.example.core_api.mediators.AppProvider
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [AppProvider::class],
    modules = [DatabaseModule::class]
)
interface DatabaseComponent: DatabaseProvider {}