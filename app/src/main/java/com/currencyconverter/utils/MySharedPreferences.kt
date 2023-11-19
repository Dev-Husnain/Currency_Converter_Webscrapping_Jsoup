package com.currencyconverter.utils

import android.content.SharedPreferences
import javax.inject.Inject


class MySharedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var isAppPurchased: Boolean
        get() = sharedPreferences.getBoolean("IS_APP_PURCHASED_KEY", false)
        set(value) = sharedPreferences.edit().putBoolean("IS_APP_PURCHASED_KEY", value).apply()


    var fromCountry: String
        get() = sharedPreferences.getString("FROM_COUNTRY_KEY", "USD").toString()
        set(value) = sharedPreferences.edit().putString("FROM_COUNTRY_KEY", value).apply()

    var toCountry: String
        get() = sharedPreferences.getString("TO_COUNTRY_KEY", "PKR").toString()
        set(value) = sharedPreferences.edit().putString("TO_COUNTRY_KEY", value).apply()

}