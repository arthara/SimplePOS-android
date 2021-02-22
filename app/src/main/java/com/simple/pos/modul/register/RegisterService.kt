package com.simple.pos.modul.register

import com.simple.pos.modul.register.submodel.RegisteringUser
import com.simple.pos.shared.model.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("auth/register")
    fun register(@Body registeringUser: RegisteringUser?): Call<Token?>
}