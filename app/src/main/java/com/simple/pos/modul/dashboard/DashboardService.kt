package com.simple.pos.modul.dashboard

import com.simple.pos.shared.model.Store
import retrofit2.http.GET
import retrofit2.Call

interface DashboardService {
    @GET("stores")
    fun retrieveStore(): Call<Store?>
}