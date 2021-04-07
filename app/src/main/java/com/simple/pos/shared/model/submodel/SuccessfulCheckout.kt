package com.simple.pos.shared.model.submodel

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.model.ReceiptItem

class SuccessfulCheckout: Receipt() {
    @SerializedName("item")
    lateinit var receiptItems: Array<ReceiptItem>

    fun getSubtotal(): Double {
        var total = 0.0

        receiptItems.forEach {
            total += it.unitTotal * it.productHistory.sellingPrice
        }

        return total
    }
}