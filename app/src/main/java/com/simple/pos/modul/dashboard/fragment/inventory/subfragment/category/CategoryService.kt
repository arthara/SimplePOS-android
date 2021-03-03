package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import com.simple.pos.shared.model.Category
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.Response

interface CategoryService {
    @GET("categories")
    fun retrieveCategories(): Call<Array<Category?>>

    @DELETE("categories/{id}")
    fun deleteCategory(@Path("id") categoryId: Int): Call<Response<Void>>
}