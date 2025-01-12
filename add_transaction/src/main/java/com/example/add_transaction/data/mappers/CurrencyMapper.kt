package com.example.add_transaction.data.mappers

import com.example.add_transaction.presentation.models.Currency
import javax.inject.Inject

class CurrencyMapper @Inject constructor() {

    fun mapCurrencyMapToList(currencyMap: Map<String, String>): List<Currency> =
        currencyMap.map {
            Currency(
                title = it.key.uppercase(),
                description = it.value
            )
        }
}