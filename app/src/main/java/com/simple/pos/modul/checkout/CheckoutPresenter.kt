package com.simple.pos.modul.checkout

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.model.submodel.CheckoutItem
import com.simple.pos.shared.singletondata.ActiveCheckout

class CheckoutPresenter(private val view: CheckoutContract.View) : CheckoutContract.Presenter {
    override fun showCheckoutItems() {
        view.showCheckoutItems(
                ActiveCheckout.checkout.checkoutItems.values
        )
    }

    override fun removeItem(checkoutItem: CheckoutItem) {
        ActiveCheckout.removeItem(checkoutItem)
    }

    override fun resetCheckout() {
        val checkoutItems = ActiveCheckout.checkout.checkoutItems

        checkoutItems.forEach {
            (_, checkoutItem) -> view.deleteItem(checkoutItem)
        }
        view.redirectToDashboard()
    }

    override fun calculateBottomBarValues() {
        val subTotal = ActiveCheckout.calculateSubTotalItems()
        val tax = 0.0 // TODO: Add proper logic for tax

        ActiveCheckout.checkout.tax = tax
        view.showBottomBarValues(subTotal, tax)
    }

    override fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int) {
        try {
            ActiveCheckout.changeTotalItem(checkoutItem, addedValue)
        }catch (e: IllegalArgumentException) {
            view.showInvalidTotalItemError(checkoutItem.total)
        }
    }

    override fun createHoldCheckout() {
        if(ActiveCheckout.checkout.checkoutItems.isEmpty()) {
            view.showCantHoldCheckoutWithZeroItem()
            return
        }

        CheckoutInteractor.requestCreateHoldCheckout(
                ActiveCheckout.convertToHoldCheckout(),
                object : RequestCallback<HoldCheckout>{
                    override fun requestSuccess(data: HoldCheckout) {
                        resetCheckout()
                        view.redirectToDashboard()
                    }

                    override fun requestError(message: String?) {
                        message?.let { Log.d(TAG, it) }
                    }
                }
        )
    }
}