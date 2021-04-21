package com.simple.pos.shared.singletondata

import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.model.HoldCheckoutItem
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.submodel.Checkout
import com.simple.pos.shared.model.submodel.CheckoutItem

object ActiveCheckout {
    private var _checkout: Checkout = Checkout()
    val checkout get() = _checkout.clone()
    val checkoutItemCount get() = _checkout.checkoutItems.size

    fun resetCheckout() {
        _checkout = Checkout()
    }

    fun addNewItem(product: Product){
        _checkout.checkoutItems[product.id] = CheckoutItem(product)
    }

    fun removeItem(checkoutItem: CheckoutItem) {
        _checkout.checkoutItems.remove(checkoutItem.id)
    }

    fun resetItems() {
        _checkout.checkoutItems.clear()
    }

    fun calculateSubTotalItems(): Double {
        var sumSubTotal = 0.0

        _checkout.checkoutItems.forEach {
            (_, checkoutItem) -> sumSubTotal += checkoutItem.unitTotal * checkoutItem.sellingPrice
        }

        return sumSubTotal
    }

    fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int) {
        try {
            _checkout.checkoutItems[checkoutItem.id]!!.unitTotal += addedValue
        } catch (e: IllegalArgumentException){
            throw e
        }
    }

    fun changeActivecheckout(holdCheckout: HoldCheckout) {
        _checkout = Checkout().apply {
            holdCheckout.holdCheckoutItems.forEach {
                checkoutItems[it.product.id] = CheckoutItem(it.product).apply {
                    unitTotal = it.unitTotal
                }
            }
        }
    }

    fun convertToHoldCheckout() : HoldCheckout {
        val holdCheckoutItems = ArrayList<HoldCheckoutItem>()
        
        _checkout.checkoutItems.forEach {(_, checkoutItem) ->
            holdCheckoutItems.add(
                    HoldCheckoutItem(checkoutItem.unitTotal, checkoutItem)
            )
        }

        return HoldCheckout(holdCheckoutItems.toTypedArray())
    }

    fun retrieveCheckoutItemAt(index: Int): CheckoutItem {
        return _checkout.checkoutItems.values.elementAt(index).clone()
    }
}