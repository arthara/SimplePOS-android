package com.simple.pos.modul.profiluser.profilnote.update

import com.simple.pos.shared.model.Store
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteStoreUpdateService {
    @POST("users/update-note/{id}")
    fun updateNoteProfile(@Body store: Store, @Path("id") id: Int): Call<Store>

    @GET("stores")
    fun retrieveStore(): Call<Store?>
}