package com.simple.pos.modul.dashboard.fragment.main

import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {
    @GET("receipt-items/total/{date}")
    fun retrieveTotalSales(@Path("date") date: String): Call<TotalSales?>

    @GET("receipt-items/top/{date}")
    fun retrieveTopSales(@Path("date") date: String): Call<TopSales?>
}