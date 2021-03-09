package com.simple.pos.modul.editcategory

import com.simple.pos.shared.model.Category
import retrofit2.http.Body
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path

interface EditCategoryService {
    @PUT("categories/{id}")
    fun editCategory(@Body category: Category, @Path("id") id: Int): Call<Category?>
}