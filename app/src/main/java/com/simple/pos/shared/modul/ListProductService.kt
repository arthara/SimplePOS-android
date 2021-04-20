package com.simple.pos.shared.modul

import com.simple.pos.shared.apiresponse.APIResponseCollection
import com.simple.pos.shared.model.Product
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface ListProductService {
    @GET("products")
    fun retrieveProducts(): Call<APIResponseCollection<Array<Product>?>>

    @GET("products/of-category/{id}")
    fun retrieveProductofCategory(@Path("id") id: Int): Call<APIResponseCollection<Array<Product>>>
}