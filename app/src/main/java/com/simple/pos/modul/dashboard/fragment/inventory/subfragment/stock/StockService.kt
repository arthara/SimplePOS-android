package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import com.simple.pos.shared.modul.ListProductService
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface StockService: ListProductService {
    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Void?>
}