package com.simple.pos.modul.checkout

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.model.submodel.CheckoutItem

interface CheckoutContract {
    interface View {
        fun showCheckoutItems()
        fun deleteItem(checkoutItem: CheckoutItem)
        fun redirectToDashboard()
        fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int)
        fun refreshBottomBarValues()
        fun showBottomBarValues(subTotal: Double, tax: Double)
        fun showInvalidTotalItemError(maxTotal: Int)
        fun addTaxValue()
        fun showTaxNumerator(tax: Double)
        fun redirectToCheckoutDetail()
        fun redirectToHoldCheckout()
        fun showCantHoldCheckoutWithZeroItem()
    }

    interface Presenter {
        fun removeItem(checkoutItem: CheckoutItem)
        fun resetCheckout()
        fun calculateBottomBarValues()
        fun taxInitial()
        fun setCurrentTaxPercent(taxNumerator: Double)
        fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int)
        fun createHoldCheckout()
    }

    interface Interactor {
        fun requestCreateHoldCheckout(holdCheckout: HoldCheckout, callback: RequestCallback<HoldCheckout>)
    }

    interface ItemView {
        fun bind(checkoutItem: CheckoutItem)
    }

    interface ItemsPresenter {
        fun onBindItemViewAtPosition(position: Int, itemView: ItemView)
    }
}