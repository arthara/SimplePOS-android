package com.simple.pos.modul.checkout

import com.simple.pos.shared.singletondata.ActiveCheckout

class CheckoutRecyclerPresenter : CheckoutContract.ItemsPresenter {
    val checkoutItemCount get() = ActiveCheckout.checkoutItemCount

    override fun onBindItemViewAtPosition(position: Int, itemView: CheckoutContract.ItemView) {
        itemView.bind(ActiveCheckout.retrieveCheckoutItemAt(position))
    }
}