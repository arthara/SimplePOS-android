package com.simple.pos.modul.holdcheckout

import com.simple.pos.shared.model.HoldCheckout
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface HoldCheckoutService {
    @GET(HoldCheckout.API_PREFIX)
    fun retrieveHoldCheckouts(): Call<ArrayList<HoldCheckout>>

    @DELETE(HoldCheckout.API_PREFIX + "/{id}")
    fun deleteHoldCheckout(@Path("id") holdCheckoutId: Int): Call<Unit>
}