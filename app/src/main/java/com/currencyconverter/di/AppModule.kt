package com.currencyconverter.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import com.currencyconverter.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun connectivityManager(@ApplicationContext appContext: Context): ConnectivityManager =
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Singleton
    @Provides
    fun sharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(
            appContext.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )


    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Singleton
    @Provides
    fun providesMainCoroutineDispatcher(): MainCoroutineDispatcher = Dispatchers.Main

    @Singleton
    @Provides
    @Named("CoroutineScopeMain")
    fun providesCoroutineScopeMain(): CoroutineScope = CoroutineScope(Dispatchers.Main)


}