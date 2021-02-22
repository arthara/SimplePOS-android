package com.simple.pos.modul.login

import com.simple.pos.shared.apiresponse.APIResponseCollection
import com.simple.pos.shared.model.Token
import com.simple.pos.shared.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth/login")
    fun login(@Body user: User?): Call<Token?>

    @POST("auth/refresh")
    fun refresh(): Call<Token?>

    @POST("auth/me")
    fun me(): Call<APIResponseCollection<User?>?>
}