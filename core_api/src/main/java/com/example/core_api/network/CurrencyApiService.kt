package com.example.core_api.network

import retrofit2.http.GET

interface CurrencyApiService {

    @GET("npm/@fawazahmed0/currency-api@latest/v1/currencies.json")
    suspend fun getCurrencies(): Map<String, String>
}