package com.example.core_api.mediators

import com.example.core_api.database.DatabaseProvider

interface ProvidersFacade: MediatorsProvider, AppProvider, DatabaseProvider