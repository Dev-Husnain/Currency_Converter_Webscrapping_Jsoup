package com.currencyconverter.domain.adapters

interface ItemClickListener {
    fun <T, D, R> onItemClick(
        position: Int = -1,
        data: T,
        actionType: D,
        selectedView: R,
    )
}