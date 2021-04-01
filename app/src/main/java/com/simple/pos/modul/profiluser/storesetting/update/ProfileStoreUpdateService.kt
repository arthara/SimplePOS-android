package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.shared.model.Store
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileStoreUpdateService {
    @PUT("users/{id}")
    fun updateProfile(@Body store: Store, @Path("id") id: Int): Call<Store>
}