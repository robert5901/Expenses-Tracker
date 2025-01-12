package com.example.add_transaction.data.repository

import android.util.Log
import com.example.add_transaction.data.mappers.CurrencyMapper
import com.example.add_transaction.presentation.models.Currency
import com.example.core_api.network.CurrencyApiService
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val currencyApiService: CurrencyApiService,
    private val currencyMapper: CurrencyMapper
) {

    suspend fun getCurrencyList(): List<Currency> {
        return try {
            val currenciesMap = currencyApiService.getCurrencies()
            currencyMapper.mapCurrencyMapToList(currenciesMap)
        } catch (e: Exception) {
            Log.e("CurrencyRepository", "Failed to fetch currencies", e)
            getDefaultCurrencyList()
        }
    }

    private fun getDefaultCurrencyList(): List<Currency> {
        return listOf(
            Currency("RUB", "Russian Ruble")
        )
    }
}