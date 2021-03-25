package com.simple.pos.shared.model.submodel

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.Product

class CheckoutItem(product: Product): Product(product.name) {
    init {
        //copy product value to checkoutitem
        product.let {
            id = it.id
            sellingPrice = it.sellingPrice
            costPrice = it.costPrice
            categoryId = it.categoryId
            picture = it.picture
            if(it.total < 0)
                throw IllegalArgumentException("cant checkout product with 0 total (stock)")
            total = it.total
        }
    }

    @SerializedName("unit_total")
    var unitTotal: Int = 1
        set(value){
            if(value < 1 || value > total)
                throw IllegalArgumentException("unitTotal should not less than 0 or more than product total")
            field = value
        }
}