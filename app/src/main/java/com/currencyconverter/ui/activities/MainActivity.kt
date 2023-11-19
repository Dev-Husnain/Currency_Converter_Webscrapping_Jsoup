package com.currencyconverter.ui.activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.currencyconverter.BuildConfig
import com.currencyconverter.R
import com.currencyconverter.base.BaseActivity
import com.currencyconverter.databinding.ActivityMainBinding
import com.currencyconverter.domain.FetchCurrency
import com.currencyconverter.domain.interfaces.FetchCurrencyListener
import com.currencyconverter.domain.models.CurrencyModel
import com.currencyconverter.utils.Constants.mToast
import com.currencyconverter.utils.Constants.moreVisibilities
import com.currencyconverter.utils.Constants.roundOff
import com.currencyconverter.utils.changeVisibility
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var fetchCurrency: FetchCurrency
    private var isSwapped = false

    @Inject
    lateinit var binding: ActivityMainBinding
    private var mCurrencyModel: CurrencyModel? = null
    private var isDataFetched = false
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                getCurrency()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getCurrency()
        binding.apply {
            cvConversionCurrency.setOnClickListener {
                swapViewPositions(clFrom, clTo)
                isSwapped = !isSwapped
                calculateOnSwap()
            }
            cvFromCurrency.setOnClickListener {
                launcher.launch(
                    Intent(
                        mContext,
                        CountriesActivity::class.java
                    ).putExtra("isFromCurrency", true)
                )
            }
            cvToCurrency.setOnClickListener {
                launcher.launch(
                    Intent(
                        mContext,
                        CountriesActivity::class.java
                    ).putExtra("isFromCurrency", false)
                )
            }
            errorLayout.btnTryAgain.setOnClickListener {
                lottieLoading.changeVisibility(0)
                llCurrency.changeVisibility(0)
                errorLayout.root.changeVisibility(1)
                getCurrency()
            }
            etFromCurrency.addTextChangedListener {
                if (!isSwapped) {
                    if (etFromCurrency.text.toString().trim().isNotBlank() && isDataFetched) {
                        calculateCurrency(it.toString().trim())
                    } else fetchCurrency
                }
            }
            etToCurrency.addTextChangedListener {
                if (isSwapped) {
                    if (etToCurrency.text.toString().trim().isNotBlank() && isDataFetched) {
                        calculateCurrency(it.toString().trim())
                    } else fetchCurrency
                }
            }
        }
    }


    override fun handleBackPressed() {
        finish()
    }

    private fun getCurrency() {
        binding.apply {
            val from = pref.fromCountry
            val to = pref.toCountry
            tvFromCurrency.text = from
            tvToCurrency.text = to
            if (from != to) {
                errorLayout.root.changeVisibility(1)
                moreVisibilities(lottieLoading, llCurrency, whichVGI = 0)
                tvFromCurrency.text = from
                tvToCurrency.text = to
                if (internetController.isInternetConnected) {
                    lottieLoading.changeVisibility(0)
                    fetchCurrency.fetchCurrency(from.lowercase(), to.lowercase(), "1", object : FetchCurrencyListener {
                            override fun onCurrencyFetched(currencyModel: CurrencyModel) {
                                mCurrencyModel = currencyModel
                                isDataFetched = true
                                calculateOnSwap()
                                moreVisibilities(lottieLoading, errorLayout.root, whichVGI = 1)
                                moreVisibilities(llCurrency, perUnitPrice, whichVGI = 0)
                                perUnitPrice.text = perUnitePrice(currencyModel)
                            }

                            override fun onError(error: String) {
                                errorLayout.root.changeVisibility(0)
                                moreVisibilities(llCurrency, lottieLoading, whichVGI = 1)
                                if (BuildConfig.DEBUG) errorLayout.tvError.text = error
                                errorLayout.tvError.text = error
                                errorLayout.lottieError.setAnimation(R.raw.oops_error)
                            }
                        })
                } else {
                    errorLayout.lottieError.setAnimation(R.raw.no_internet)
                    errorLayout.root.changeVisibility(0)
                }
            }
            else {
                lottieLoading.changeVisibility(1)
                "Same currencies can not be converted".mToast()
            }
        }
    }


    private fun swapViewPositions(viewFrom: View, viewTo: View) {
        val translationX1: Float
        val translationY1: Float
        val translationX2: Float
        val translationY2: Float
        if (isSwapped) {
            translationX1 = 0f
            translationY1 = 0f
            translationX2 = 0f
            translationY2 = 0f
        } else {
            translationX1 = viewTo.x - viewFrom.x
            translationY1 = viewTo.y - viewFrom.y
            translationX2 = viewFrom.x - viewTo.x
            translationY2 = viewFrom.y - viewTo.y
        }
        val translationAnimator1 = ObjectAnimator.ofFloat(viewFrom, "translationX", translationX1)
        val translationAnimator2 = ObjectAnimator.ofFloat(viewFrom, "translationY", translationY1)
        val translationAnimator3 = ObjectAnimator.ofFloat(viewTo, "translationX", translationX2)
        val translationAnimator4 = ObjectAnimator.ofFloat(viewTo, "translationY", translationY2)
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(
            translationAnimator1,
            translationAnimator2,
            translationAnimator3,
            translationAnimator4
        )
        animatorSet.duration = 500
        animatorSet.start()


    }

    private fun calculateOnSwap() {
        binding.apply {
            if (isSwapped) {
                etFromCurrency.isEnabled = false
                etToCurrency.isEnabled = true
            } else {
                etFromCurrency.isEnabled = true
                etToCurrency.isEnabled = false
            }
            if (isDataFetched) {
                if (isSwapped) {
                    if (etToCurrency.text.toString().trim().isNotBlank() && isDataFetched) {
                        calculateCurrency(etToCurrency.text.toString().trim())
                    } else fetchCurrency
                } else {
                    if (etFromCurrency.text.toString().trim().isNotBlank() && isDataFetched) {
                        calculateCurrency(etFromCurrency.text.toString().trim())
                    } else fetchCurrency
                }
            }
        }
    }

    private fun perUnitePrice(model: CurrencyModel): String {
        return "(1 ${model.fromCurrency.uppercase()} to 1 ${model.toCurrency.uppercase()}: " +
                "${model.perUnitFromValue} & " + "1 ${model.toCurrency.uppercase()} to " +
                "1 ${model.fromCurrency.uppercase()}:" + " ${model.perUnitToValue})"
    }

    private fun calculateCurrency(text: String) {
        if (mCurrencyModel != null) {
            try {
                val fromValue =
                    if (isSwapped) mCurrencyModel!!.perUnitToValue.toDouble() else mCurrencyModel!!.perUnitFromValue.toDouble()
                val totalValue = (fromValue * text.toDouble()).roundOff()
                if (isSwapped) binding.etFromCurrency.setText("$totalValue")
                else binding.etToCurrency.setText("$totalValue")

            } catch (e: Exception) {
                Log.d("cvv", "calculateCurrency: ${e.message}")
            }
        } else {
            getCurrency()
        }

    }

}