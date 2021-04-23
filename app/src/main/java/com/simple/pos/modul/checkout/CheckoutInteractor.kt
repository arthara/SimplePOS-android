package com.simple.pos.modul.checkout

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.HoldCheckout

object CheckoutInteractor
    : BaseInteractor<CheckoutService>(CheckoutService::class.java), CheckoutContract.Interactor {
    override fun requestCreateHoldCheckout(
        holdCheckout: HoldCheckout,
        callback: RequestCallback<HoldCheckout>
    ) {
        service.createHoldCheckout(holdCheckout)
            .enqueue(RetrofitCallback(callback, TAG, "requestHoldCheckout"))
    }
}