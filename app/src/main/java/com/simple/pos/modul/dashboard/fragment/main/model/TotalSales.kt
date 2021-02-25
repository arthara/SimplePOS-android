package com.simple.pos.modul.dashboard.fragment.main.model

import com.google.gson.annotations.SerializedName

class TotalSales(var date: String,
                 @SerializedName("total_sales") var totalSales: Int,
                 @SerializedName("gross_profit") var grossProfit: Double)