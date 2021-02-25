package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class Product(var name: String) {
    var id: Int = -1
    @SerializedName("category_id")
    var categoryId: Int = -1
    var picture: String? = null
    var total: Int = -1
    @SerializedName("selling_price")
    var sellingPrice: Double = -1.0
    @SerializedName("cost_price")
    var costPrice: Double = -1.0
}