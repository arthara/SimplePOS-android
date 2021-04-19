package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class HoldCheckoutItem(
        @SerializedName("unit_total")
        val unitTotal: Int,
        val product: Product
) {
    val id: Int = 0
}