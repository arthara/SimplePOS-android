package com.simple.pos.modul.dashboard.fragment.main

import com.simple.pos.shared.model.Store
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {
    @GET("stores")
    fun retrieveStore(): Call<Store?>

    @GET("receipt-items/total/{date}")
    fun retrieveTotalSales(@Path("date") date: String): Call<Store?>

    @GET("receipt-items/top/{date}")
    fun retrieveTopSales(@Path("date") date: String): Call<Store?>

}