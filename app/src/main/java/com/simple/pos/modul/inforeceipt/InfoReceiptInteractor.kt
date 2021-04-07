package com.simple.pos.modul.inforeceipt

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.submodel.SuccessfulCheckout
import com.simple.pos.shared.retrofit.ServiceGenerator

object InfoReceiptInteractor : InfoReceiptContract.Interactor {
    private val service = ServiceGenerator.createService(InfoReceiptService::class.java)

    override fun requestRetrieveReceipt(receiptId: Int, callback: RequestCallback<SuccessfulCheckout>) {
        service.retrieveReceipt(receiptId)
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveReceipt"))
    }
}