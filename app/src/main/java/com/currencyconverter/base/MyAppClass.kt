package com.currencyconverter.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyAppClass : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

    }

    companion object {
        var appContext: Context? = null
    }


}