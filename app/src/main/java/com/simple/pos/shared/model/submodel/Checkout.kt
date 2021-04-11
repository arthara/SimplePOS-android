package com.simple.pos.shared.model.submodel

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.Receipt

class Checkout: Receipt() {
    @SerializedName("items")
    val checkoutItems: LinkedHashMap<Int, CheckoutItem> = LinkedHashMap()


    fun reconnectCheckoutItemWithUpdatedProducts(products: Array<Product>){
        products.forEach {
            if(it.total <= 0)
                checkoutItems.remove(it.id)
            checkoutItems[it.id]?.refreshProduct(it)
        }
    }
}