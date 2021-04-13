package com.simple.pos.shared.singletondata

import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.submodel.Checkout
import com.simple.pos.shared.model.submodel.CheckoutItem

object ActiveCheckout {
    private var _checkout: Checkout = Checkout()
    val checkout get() = _checkout.clone()

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

    fun calculateTaxofSubTotal(taxPercent: Double): Double{
        val sumSubTotalAfterTax = 0.0
        if(calculateSubTotalItems() > 0 && taxPercent > 0){
            return calculateSubTotalItems() * taxPercent
        }

        return sumSubTotalAfterTax
    }

    fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int) {
        try {
            _checkout.checkoutItems[checkoutItem.id]!!.unitTotal += addedValue
        } catch (e: IllegalArgumentException){
            throw e
        }
    }
}