package com.simple.pos.modul.detailcheckout

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.PaymentMethod
import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.singletondata.ActiveCheckout

class DetailCheckoutPresenter(private val view: DetailCheckoutContract.View)
    : DetailCheckoutContract.Presenter {

    override fun changePaymentMethod(shownPaymentMethod: String) {
        try {
            ActiveCheckout.checkout.paymentMethod = PaymentMethod.valueOf(shownPaymentMethod)
        }catch (exception: Exception){

        }
    }

    override fun createReceipt() {
        val checkout = ActiveCheckout.checkout

        view.startLoading()
        DetailCheckoutInteractor.requestCreateReceipt(checkout, object : RequestCallback<Receipt> {
            override fun requestSuccess(data: Receipt) {
                view.stopLoading()
                ActiveCheckout.resetCheckout()
                view.redirectToDetailCheckoutSuccess(data.id)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }

    override fun showCheckoutData() {
        view.showCheckoutData(ActiveCheckout.checkout)
    }
}
