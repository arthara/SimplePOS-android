package com.simple.pos.modul.detailcheckout

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.PaymentMethod
import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.singletondata.ActiveCheckout
import com.simple.pos.shared.util.StoreUtil

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
        checkout.note = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData.noteReceipt

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
