package com.simple.pos.modul.product.update

import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UpdateProductService {
    @Multipart
    @POST("update-product-response/{id}")
    fun updateProduct(@Part("category_id") categoryId: Int,
                      @Part("name") name: RequestBody,
                      @Part picture: MultipartBody.Part?,
                      @Part("total") total: Int,
                      @Part("selling_price") sellingPrice: Double,
                      @Part("cost_price") costPrice: Double,
                      @Path("id") id: Int): Call<Product?>

    //fun updateProduct(@Body product: Product, @Path("id") id: Int): Call<Product?>

    @GET("categories/{id}")
    fun getCategoryName(@Path("id") id: Int): Call<Category?>


}