package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

open class Receipt {
    var id = -1
    @SerializedName("receipt_time")
    lateinit var receiptTime: String
    @SerializedName("customer_name")
    var customerName: String? = null
    @SerializedName("customer_phone")
    var customerPhone: String? = null
    @SerializedName("tax")
    var tax: Double? = null
    @SerializedName("discount")
    var discount: Double? = null
    @SerializedName("other_charges")
    var otherCharges: Double? = null
    @SerializedName("note")
    var note: String? = null
    @SerializedName("payment_method")
    var paymentMethod: PaymentMethod = PaymentMethod.CASH
}