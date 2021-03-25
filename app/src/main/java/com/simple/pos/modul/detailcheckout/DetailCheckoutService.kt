package com.simple.pos.modul.detailcheckout

import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.model.submodel.Checkout
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface DetailCheckoutService {
    @POST("receipts/checkout")
    fun createReceipt(@Body checkout: Checkout): Call<Receipt?>
}