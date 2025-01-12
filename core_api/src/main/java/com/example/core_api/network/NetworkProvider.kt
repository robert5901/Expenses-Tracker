package com.example.core_api.network

interface NetworkProvider {

    fun provideCurrencyApiService(): CurrencyApiService
}