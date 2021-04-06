package com.simple.pos.modul.inforeceipt

import com.simple.pos.shared.apiresponse.APIResponseCollection
import com.simple.pos.shared.model.submodel.SuccessfulCheckout
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface InfoReceiptService {
    @GET("receipts/{id}")
    fun retrieveReceipt(@Path("id") receiptId: Int)
            : Call<APIResponseCollection<SuccessfulCheckout>>
}