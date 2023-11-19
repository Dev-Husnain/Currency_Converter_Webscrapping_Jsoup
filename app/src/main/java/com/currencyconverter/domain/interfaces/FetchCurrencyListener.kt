package com.currencyconverter.domain.interfaces

import com.currencyconverter.domain.models.CurrencyModel

interface FetchCurrencyListener {
    fun onCurrencyFetched(currencyModel: CurrencyModel)
    fun onError(error: String)
}