package com.example.core_api.mediators

import com.example.core_api.database.DatabaseProvider
import com.example.core_api.network.NetworkProvider

interface ProvidersFacade: MediatorsProvider, AppProvider, DatabaseProvider, NetworkProvider