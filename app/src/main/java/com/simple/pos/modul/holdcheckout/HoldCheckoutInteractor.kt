package com.simple.pos.modul.holdcheckout

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.HoldCheckout

object HoldCheckoutInteractor: BaseInteractor<HoldCheckoutService>(HoldCheckoutService::class.java) {
    fun requestRetrieveHoldCheckouts(callback: RequestCallback<ArrayList<HoldCheckout>>) {
        service.retrieveHoldCheckouts()
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveHoldCheckouts"))
    }

    fun requestDeleteHoldCheckouts(holdCheckout: HoldCheckout, callback: RequestCallback<Unit?>) {
        service.deleteHoldCheckout(holdCheckout.id)
                .enqueue(RetrofitCallback(callback, TAG, "requestDeleteHoldCheckouts"))
    }
}