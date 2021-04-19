package com.simple.pos.modul.holdcheckout

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.singletondata.ActiveCheckout

class HoldCheckoutPresenter(private val view: HoldCheckoutContract.View)
    : HoldCheckoutContract.Presenter {
    override fun showHoldCheckouts() {
        HoldCheckoutInteractor.requestRetrieveHoldCheckouts(object : RequestCallback<ArrayList<HoldCheckout>> {
            override fun requestSuccess(data: ArrayList<HoldCheckout>) {
                view.showHoldCheckouts(data)
            }

            override fun requestError(message: String?) {
                if (message != null) {
                    Log.d(TAG, message)
                }
            }
        })
    }

    override fun deleteHoldCheckout(holdCheckout: HoldCheckout) {
        view.startLoading()

        HoldCheckoutInteractor.requestDeleteHoldCheckouts(holdCheckout, object : RequestCallback<Unit?> {
            override fun requestSuccess(data: Unit?) {
                view.removeHoldCheckoutFromList(holdCheckout)
                view.stopLoading()
            }

            override fun requestError(message: String?) {
                if (message != null) {
                    Log.d(TAG, message)
                }
                view.stopLoading()
            }
        })
    }

    override fun chooseCheckout(holdCheckout: HoldCheckout) {
        view.startLoading()
        ActiveCheckout.changeActivecheckout(holdCheckout)

        HoldCheckoutInteractor.requestDeleteHoldCheckouts(holdCheckout, object : RequestCallback<Unit?> {
            override fun requestSuccess(data: Unit?) {
                view.stopLoading()
                view.redirectToCheckout()
            }

            override fun requestError(message: String?) {
                if (message != null) {
                    Log.d(TAG, message)
                }
                view.stopLoading()
            }
        })
    }
}