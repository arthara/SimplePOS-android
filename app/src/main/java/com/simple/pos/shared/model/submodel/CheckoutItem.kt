package com.simple.pos.shared.model.submodel

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.Product

class CheckoutItem(product: Product): Product(product.name) {
    @SerializedName("unit_total")
    var unitTotal: Int = 1
        set(value) {
        if(value < 1 || value > total)
            throw IllegalArgumentException("unitTotal should not less than 1 or more than product total")
        field = value
    }


    init {
        castProductToCheckoutItem(product)

        if (product.total <= 0)
            throw IllegalArgumentException("cant checkout product with 0 total (stock)")
        total = product.total
    }


    fun refreshProduct(product: Product) {
        product.let{
            castProductToCheckoutItem(product)
            //if unit total is less than new total reduce it to total
            if(unitTotal > total)
                unitTotal = total
        }
    }

    private fun castProductToCheckoutItem(product: Product) {
        //copy product value to checkoutitem
        product.let {
            id = it.id
            sellingPrice = it.sellingPrice
            costPrice = it.costPrice
            categoryId = it.categoryId
            picture = it.picture
        }
    }

    fun clone() : CheckoutItem {
        val checkoutItem = CheckoutItem(this)

        checkoutItem.unitTotal = unitTotal

        return checkoutItem
    }
}