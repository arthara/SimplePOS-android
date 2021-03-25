package com.simple.pos.shared.model.submodel

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.Receipt

class Checkout: Receipt() {
    @SerializedName("items")
    val checkoutItems: LinkedHashMap<Int, CheckoutItem> = LinkedHashMap()
}