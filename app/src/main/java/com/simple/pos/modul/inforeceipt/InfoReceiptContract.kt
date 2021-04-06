package com.simple.pos.modul.inforeceipt

import com.bumptech.glide.load.model.GlideUrl
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.model.submodel.SuccessfulCheckout

interface InfoReceiptContract {
    interface View {
        fun showReceipts(data: SuccessfulCheckout)
        fun showStore(store: Store)
        fun redirectToDashboard()
        fun showLogo(glideUrl: GlideUrl)
    }

    interface Presenter {
        fun retrieveReceipt(receiptId: Int)
        fun retrieveStore()
        fun showLogo(logo: String?)
    }

    interface Interactor {
        fun requestRetrieveReceipt(receiptId: Int, callback: RequestCallback<SuccessfulCheckout>)
    }
}