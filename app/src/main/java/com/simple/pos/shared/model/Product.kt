package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Product(var name: String) : Serializable {
    var id: Int = -1
    @SerializedName("category_id")
    var categoryId: Int = -1
    var picture: String? = null
    var total: Int = 0
        set(value){
            if(value < 0)
                throw IllegalArgumentException("Total product shouldn't less than 0")
            field = value
        }
    @SerializedName("selling_price")
    var sellingPrice: Double = -1.0
    @SerializedName("cost_price")
    var costPrice: Double = -1.0
}