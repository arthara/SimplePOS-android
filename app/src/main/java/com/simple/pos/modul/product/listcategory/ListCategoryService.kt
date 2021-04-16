package com.simple.pos.modul.product.listcategory


import com.simple.pos.modul.product.listcategory.model.ListCategory
import com.simple.pos.shared.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface ListCategoryService {

    @GET("categories-with-counts")
    fun retrieveCategoriesWithCounts(): Call<Array<ListCategory>>


    @GET("categories")
    fun retrieveCategories(): Call<Array<Category>>

}