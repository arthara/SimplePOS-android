package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class HoldCheckout(
        @SerializedName("items")
        val holdCheckoutItems: Array<HoldCheckoutItem>
) {
    val id: Int = 0
    val totalSellingPrice: Double get() {
            var totalSellingprice = 0.0

             holdCheckoutItems.forEach {
                     totalSellingprice += it.product.sellingPrice * it.unitTotal
             }

            return totalSellingprice
    }

    companion object {
        const val API_PREFIX = "held-checkout"
    }
}