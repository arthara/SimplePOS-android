package com.simple.pos.shared.singletondata

import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.submodel.Checkout
import com.simple.pos.shared.model.submodel.CheckoutItem
import java.lang.IllegalArgumentException

object ActiveCheckout {
    var checkout: Checkout = Checkout()

    fun resetCheckout() {
        checkout = Checkout()
    }

    fun addNewItem(product: Product){
        checkout.checkoutItems[product.id] = CheckoutItem(product)
    }

    fun removeItem(checkoutItem: CheckoutItem) {
        checkout.checkoutItems.remove(checkoutItem.id)
    }

    fun resetItems() {
        checkout.checkoutItems.clear()
    }

    fun calculateSubTotalItems(): Double {
        var sumSubTotal = 0.0

        checkout.checkoutItems.forEach {
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
            checkout.checkoutItems[checkoutItem.id]!!.unitTotal += addedValue
        } catch (e: IllegalArgumentException){
            throw e
        }
    }
}