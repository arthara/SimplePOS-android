package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.shared.model.Store
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileStoreUpdateService {

    @POST("users/{id}")
    fun updateProfile(@Body store: Store, @Path("id") id: Int): Call<Store>

    @GET("stores")
    fun retrieveStore(): Call<Store?>

    @Multipart
    @POST("users/update-image/{id}")
    fun updateImage(
            @Path("id") id: Int,
            @Part logo: MultipartBody.Part?
    ): Call<Store?>

}