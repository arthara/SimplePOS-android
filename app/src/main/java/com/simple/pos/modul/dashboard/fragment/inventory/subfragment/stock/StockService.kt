package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import com.simple.pos.shared.modul.ListProductService
import com.simple.pos.shared.modul.UpdateProductService
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Path

interface StockService: ListProductService, UpdateProductService {
    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id: Int): Call<Void?>
}