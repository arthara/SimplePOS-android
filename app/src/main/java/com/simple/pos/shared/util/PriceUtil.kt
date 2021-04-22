package com.simple.pos.shared.util

import java.text.NumberFormat
import java.util.*

object PriceUtil {
    private val priceFormat =
            NumberFormat.getCurrencyInstance(Locale("in", "id")).apply {
                maximumFractionDigits = 2 // maximum decimal number
            }

    fun convertValueToCurrency(price: Double): String {
        return priceFormat.format(price)
    }
}