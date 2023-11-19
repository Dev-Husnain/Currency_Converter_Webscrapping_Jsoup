package com.currencyconverter.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.currencyconverter.utils.InternetController
import com.currencyconverter.utils.MySharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    lateinit var mContext: AppCompatActivity


    @Inject
    lateinit var pref: MySharedPreferences

    private lateinit var mHandler: Handler


    @Inject
    lateinit var internetController: InternetController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                callBackPress()
            }
        })

    }

    fun callBackPress() {
        handleBackPressed()
    }

    fun baseHandler(): Handler {
        if (!::mHandler.isInitialized) {
            mHandler = Handler(Looper.getMainLooper())
        }
        return mHandler
    }

    abstract fun handleBackPressed()


}