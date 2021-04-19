package com.simple.pos.shared.util

import java.text.NumberFormat
import java.util.*

class ConverterUtil {

    companion object{
        @JvmStatic
        fun formatRupiah(number: Double): String? {
            val localeID = Locale("in", "ID")
            val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
            return formatRupiah.format(number)
        }
    }

}