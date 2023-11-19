package com.currencyconverter.domain.models
import androidx.annotation.Keep
@Keep
data class CurrencyModel(
    var countryCode: String = "",
    var countryName: String = "",
    var fromCurrency: String = "",
    var toCurrency: String = "",
    var userInput: String = "",
    var resultValue: String = "",
    var perUnitToValue: String = "",
    var perUnitFromValue: String = ""

)