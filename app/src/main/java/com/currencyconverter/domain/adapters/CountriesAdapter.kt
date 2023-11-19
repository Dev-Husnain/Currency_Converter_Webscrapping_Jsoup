package com.currencyconverter.domain.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.currencyconverter.databinding.RowCountriesBinding
import com.currencyconverter.domain.models.CurrencyModel
import com.currencyconverter.ui.activities.CountriesActivity.Companion.countriesList
import com.currencyconverter.ui.activities.tempCode
import com.currencyconverter.utils.changeVisibility
import javax.inject.Inject

class CountriesAdapter @Inject constructor() :
    ListAdapter<CurrencyModel, CountriesAdapter.CountryVH>(CountryDiffUtil()) {

    fun filterList(text: String) {
        val newList = countriesList.filter {
            it.countryName.contains(text, true) || it.countryCode.contains(text, true)
        }
        submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryVH {
        return CountryVH(
            RowCountriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryVH, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class CountryVH(private val binding: RowCountriesBinding) : ViewHolder(binding.root) {
        fun bindData(item: CurrencyModel?) {
            item?.let { model ->
                binding.apply {
                    tvCountry.text = model.countryCode
                    tvCountryName.text = model.countryName
                    tvCountryCode.text = model.countryCode
                    if (model.countryCode == tempCode) ivChecked.changeVisibility(0)
                    else ivChecked.changeVisibility(2)
                }
            }
        }

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos >= 0) {
                    val model = getItem(pos)
                    val prevPos = currentList.indexOfFirst { it.countryCode == tempCode }
                    tempCode = model.countryCode
                    notifyItemChanged(prevPos)
                    notifyItemChanged(pos)
                }
            }
        }
    }

    class CountryDiffUtil : DiffUtil.ItemCallback<CurrencyModel>() {
        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem.countryCode == newItem.countryCode
        }

        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }
    }
}