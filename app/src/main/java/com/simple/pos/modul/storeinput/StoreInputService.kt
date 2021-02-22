package com.simple.pos.modul.storeinput

import com.simple.pos.shared.model.Store
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface StoreInputService {
    @Multipart
    @POST("stores")
    fun create(
            @Part("name") name: RequestBody,
            @Part logo: MultipartBody.Part?,
            @Part("address") address: RequestBody?,
            @Part("phone_number") phoneNumber: RequestBody?
    ): Call<Store?>
}