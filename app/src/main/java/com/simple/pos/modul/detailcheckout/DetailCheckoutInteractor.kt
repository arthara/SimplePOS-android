package com.simple.pos.modul.detailcheckout

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.model.submodel.Checkout
import com.simple.pos.shared.retrofit.ServiceGenerator

object DetailCheckoutInteractor : DetailCheckoutContract.Interactor{
    private val service = ServiceGenerator.createService(DetailCheckoutService::class.java)

    override fun requestCreateReceipt(checkout: Checkout, callback: RequestCallback<Receipt>) {
        service.createReceipt(checkout)
                .enqueue(RetrofitCallback(callback, TAG, "requestCreateReceipt"))
    }
}