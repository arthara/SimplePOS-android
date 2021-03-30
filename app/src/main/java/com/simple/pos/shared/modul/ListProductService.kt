package com.simple.pos.shared.modul

import com.simple.pos.shared.apiresponse.APIResponseCollection
import com.simple.pos.shared.model.Product
import retrofit2.http.GET
import retrofit2.Call

interface ListProductService {
    @GET("products")
    fun retrieveProducts(): Call<APIResponseCollection<Array<Product>?>>
}