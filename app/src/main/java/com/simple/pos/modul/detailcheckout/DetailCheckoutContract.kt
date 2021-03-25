package com.simple.pos.modul.detailcheckout

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.model.submodel.Checkout

interface DetailCheckoutContract {
    interface View {
        fun redirectToCheckoutActivity()
        fun redirectToDetailCheckoutSuccess(receiptId: Int)
        fun startLoading()
        fun stopLoading()
        fun showCheckoutData(checkout: Checkout)
    }

    interface Presenter {
        fun changePaymentMethod(shownPaymentMethod: String)
        fun createReceipt()
        fun showCheckoutData()
    }

    interface Interactor {
        fun requestCreateReceipt(checkout: Checkout, callback: RequestCallback<Receipt>)
    }
}