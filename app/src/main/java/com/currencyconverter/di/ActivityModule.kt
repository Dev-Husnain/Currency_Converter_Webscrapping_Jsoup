package com.currencyconverter.di

import android.app.Activity
import android.content.Context
import com.currencyconverter.databinding.ActivityCountriesBinding
import com.currencyconverter.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object ActivityModule {

    @ActivityScoped
    @Provides
    fun mainActivity(@ActivityContext appContext: Context) =
        ActivityMainBinding.inflate((appContext as Activity).layoutInflater)


    @ActivityScoped
    @Provides
    fun activityCountriesBinding(@ActivityContext appContext: Context) =
        ActivityCountriesBinding.inflate((appContext as Activity).layoutInflater)


}