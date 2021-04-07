package com.simple.pos.modul.inforeceipt

import android.util.Log
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.glide.GlideUrlUtil
import com.simple.pos.shared.model.submodel.SuccessfulCheckout
import com.simple.pos.shared.util.StoreUtil

class InfoReceiptPresenter(private val view: InfoReceiptContract.View)
    : InfoReceiptContract.Presenter {

    override fun retrieveReceipt(receiptId: Int) {
        InfoReceiptInteractor
                .requestRetrieveReceipt(receiptId, object : RequestCallback<SuccessfulCheckout> {
                    override fun requestSuccess(data: SuccessfulCheckout) {
                        view.showReceipts(data)
                    }
        
                    override fun requestError(message: String?) {
                        message?.let {
                            Log.i(TAG, it)
                            view.showErrorMessage(it)
                        }
                    }
                })
    }
    
    override fun retrieveStore() {
        view.showStore(
                (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
        )
    }

    override fun showLogo(logo: String?) {
        logo?.let {
            view.showLogo(
                    GlideUrlUtil.convertToAuthorizedUrl(logo)
            )
        }
    }
}