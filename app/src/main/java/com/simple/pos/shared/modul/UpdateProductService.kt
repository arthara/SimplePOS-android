package com.simple.pos.shared.modul

import com.simple.pos.shared.model.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateProductService {
    @PUT("products/{id}")
    fun updateProduct(@Path("id") id: Int,
                      @Body() product: Product)
    : Call<Product>
}