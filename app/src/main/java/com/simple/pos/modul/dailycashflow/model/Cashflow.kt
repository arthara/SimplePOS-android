package com.simple.pos.modul.dailycashflow.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

class Cashflow(
        var id: Int,
        @SerializedName("receipt_time")
        private var receiptTime: String,
        @SerializedName("payment_method")
        var paymentMethod: String,
        @SerializedName("total_item_sold")
        var totalItemSold: Int,
        var profit: Float) {

    companion object {
        //convert received format 2021-12-02 12:23:31
        private val receiptTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //to format 12:23
        private val dateFormat = SimpleDateFormat("HH:mm")
    }

    fun getTime(): String{
        //convert string timestamp to date then convert it back to time string
        val date = receiptTimeFormat.parse(receiptTime)

        return dateFormat.format(date)
    }
}