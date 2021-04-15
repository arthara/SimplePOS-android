package com.simple.pos.modul.checkout

import com.simple.pos.shared.model.HoldCheckout
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckoutService {
    @POST(HoldCheckout.API_PREFIX)
    fun createHoldCheckout(@Body holdCheckout: HoldCheckout) : Call<HoldCheckout>
}