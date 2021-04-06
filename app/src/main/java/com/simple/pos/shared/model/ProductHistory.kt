package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class ProductHistory(
    var id: Int,
    @SerializedName("product_id")
    var productId: Int?,
    var name: String,
    @SerializedName("selling_price")
    var sellingPrice: Double,
    @SerializedName("cost_price")
    var costPrice: Double
)
