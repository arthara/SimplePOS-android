package com.simple.pos.shared.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class ConverterUtil {

    companion object{
        @JvmStatic
        fun formatRupiah(number: Double): String? {
            val localeID = Locale("in", "ID")
            val formatter = NumberFormat.getCurrencyInstance(localeID) as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = "Rp " // Don't use null.

            formatter.decimalFormatSymbols = symbols
            return formatter.format(number)
        }
        @JvmStatic
        fun formatRupiahWithoutSymbol(number: Double): String? {

            val localeID = Locale("in", "ID")
            val formatter = NumberFormat.getCurrencyInstance(localeID) as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = ""

            formatter.decimalFormatSymbols = symbols
            return formatter.format(number)

        }
    }

}