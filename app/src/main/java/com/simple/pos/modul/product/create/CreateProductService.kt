package com.simple.pos.modul.product.create

import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.Store
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CreateProductService {
    @Multipart
    @POST("products")
    fun createProduct(
            @Part("category_id") categoryId: RequestBody,
            @Part("name") name: RequestBody,
            @Part picture: MultipartBody.Part?,
            @Part("total") total: RequestBody,
            @Part("selling_price") sellingPrice: RequestBody,
            @Part("cost_price") costPrice: RequestBody
    ): Call<Product?>
}