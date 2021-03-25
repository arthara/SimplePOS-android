package com.simple.pos.modul.checkout

import com.simple.pos.shared.model.submodel.CheckoutItem

interface CheckoutContract {
    interface View {
        fun showCheckoutItems(checkoutItems: MutableCollection<CheckoutItem>)
        fun deleteItem(checkoutItem: CheckoutItem)
        fun redirectToDashboard()
        fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int)
        fun showBottomBarValues(subTotal: Double, tax: Double)
        fun showInvalidTotalItemError(maxTotal: Int)
        fun redirectToCheckoutDetail()
    }

    interface Presenter {
        fun showCheckoutItems()
        fun removeItem(checkoutItem: CheckoutItem)
        fun resetCheckout()
        fun calculateBottomBarValues()
        fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int)
    }
}