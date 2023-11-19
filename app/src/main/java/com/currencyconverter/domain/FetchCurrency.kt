package com.currencyconverter.domain

import android.util.Log
import com.currencyconverter.domain.interfaces.FetchCurrencyListener
import com.currencyconverter.domain.models.CurrencyModel
import com.currencyconverter.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchCurrency @Inject constructor(private val coroutineScope: CoroutineScope) {
    private var currencyListener: FetchCurrencyListener? = null
    fun fetchCurrency(
        fromCurrency: String,
        toCurrency: String,
        amount: String,
        linksFetchedInterface: FetchCurrencyListener
    ) {
        this.currencyListener = linksFetchedInterface
        val link = "${Constants.BASE_URL}$fromCurrency-$toCurrency/?amount=$amount"
        val currencyModel = CurrencyModel()
        coroutineScope.launch {
            try {
                val mainDoc = Jsoup.connect(link).get()
                val resultClass = mainDoc.select("h2.result-box-conversion")
                val perUnitClass = mainDoc.select("div.result-box-c1-c2")
                if (resultClass.size > 0 && perUnitClass.size > 0) {
                    val resultAmount = resultClass.select("span.amount")
                    if (resultAmount.size > 0) {
                        val convertedAmount = resultAmount.text()
                        val perUnitFromValueText = perUnitClass.select("div").first()?.text() ?: ""
                        val perUnitToValueText = perUnitClass.select("div").last()?.text() ?: ""
                        val usdToPkrPattern = """\d+\.\d+"""
                        val pkrToUsdPattern = """\d+\.\d+"""
                        val perUnitFromValue =
                            Regex(usdToPkrPattern).find(perUnitFromValueText)?.value ?: "Not Found"
                        val perUnitToValue =
                            Regex(pkrToUsdPattern).find(perUnitToValueText)?.value ?: "Not Found"

                        currencyModel.fromCurrency = fromCurrency
                        currencyModel.toCurrency = toCurrency
                        currencyModel.userInput = amount
                        currencyModel.resultValue = convertedAmount
                        currencyModel.perUnitFromValue = perUnitFromValue
                        currencyModel.perUnitToValue = perUnitToValue


                        withContext(Dispatchers.Main) {
                            linksFetchedInterface.onCurrencyFetched(currencyModel)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            linksFetchedInterface.onError("Amount not found")
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        linksFetchedInterface.onError("Desired currency not found")
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    linksFetchedInterface.onError(e.message.toString())
                }
            }
        }
    }

    private fun getCountriesList(mainDoc: Document) {
        val selectElement = mainDoc.select("select#from_currency")
        val options = selectElement.select("option")
        for (option in options) {
            val countryCode = option.attr("value")
            val text = option.text()
            val countryName = text.split(" - ")[1]
            val listName = "countriesList" // Change this to your desired list name
            val generatedCode = buildString {
                append("$listName.add(CountriesModel(\"${countryCode}\", \"${countryName}\"))\n")
            }

            Log.d("myList", generatedCode)
        }
    }


}