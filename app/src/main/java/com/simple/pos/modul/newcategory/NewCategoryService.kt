package com.simple.pos.modul.newcategory

import com.simple.pos.shared.model.Category
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface NewCategoryService {
    @POST("categories")
    fun createCategory(@Body category: Category): Call<Category?>
}