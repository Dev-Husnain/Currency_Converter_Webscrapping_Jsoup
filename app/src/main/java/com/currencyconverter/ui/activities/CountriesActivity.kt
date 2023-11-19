package com.currencyconverter.ui.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.currencyconverter.base.BaseActivity
import com.currencyconverter.databinding.ActivityCountriesBinding
import com.currencyconverter.domain.adapters.CountriesAdapter
import com.currencyconverter.domain.models.CurrencyModel
import com.currencyconverter.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

var tempCode = "USD"

@AndroidEntryPoint
class CountriesActivity : BaseActivity() {
    @Inject
    lateinit var binding: ActivityCountriesBinding
    private var isFromCurrency = true

    @Inject
    lateinit var countriesAdapter: CountriesAdapter

    companion object {
        var countriesList = ArrayList<CurrencyModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        countriesList = Constants.countriesList()
        intent?.extras?.let {
            isFromCurrency = it.getBoolean("isFromCurrency", true)
        }
        tempCode = if (isFromCurrency) pref.fromCountry else pref.toCountry
        initViews()
        binding.apply {
            etSearch.addTextChangedListener {
                countriesAdapter.filterList(it.toString().trim())
            }
            backArrow.setOnClickListener {
                handleBackPressed()
            }
            btnApply.setOnClickListener {
                if (isFromCurrency) pref.fromCountry = tempCode
                else pref.toCountry = tempCode
                setResult(Activity.RESULT_OK)
                handleBackPressed()
            }
        }
    }

    override fun handleBackPressed() {
        finish()
    }

    private fun initViews() {
        binding.apply {
            rvCountries.layoutManager = LinearLayoutManager(mContext)
            rvCountries.adapter = countriesAdapter
            countriesAdapter.submitList(countriesList)
        }
    }
}