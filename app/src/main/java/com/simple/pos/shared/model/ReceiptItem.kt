package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class ReceiptItem(
        var id: Int,
        @SerializedName("unit_total")
        var unitTotal: Int,
        @SerializedName("product_history")
        var productHistory: ProductHistory
)