package com.simple.pos.modul.holdcheckout

import com.simple.pos.shared.model.HoldCheckout

interface HoldCheckoutContract {
    interface View {
        fun showHoldCheckouts(data: ArrayList<HoldCheckout>)
        fun removeHoldCheckoutFromList(holdCheckout: HoldCheckout)
        fun deleteHoldCheckout(holdCheckout: HoldCheckout)
        fun chooseHoldCheckout(holdCheckout: HoldCheckout)
        fun startLoading()
        fun stopLoading()
        fun redirectToCheckout()
    }

    interface Presenter {
        fun showHoldCheckouts()
        fun deleteHoldCheckout(holdCheckout: HoldCheckout)
        fun chooseCheckout(holdCheckout: HoldCheckout)
    }
}