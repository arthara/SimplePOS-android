package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

enum class PaymentMethod(val shownMethod: String) {
    //Serialized is backend value
    @SerializedName("cash")
    CASH("Tunai"),

    @SerializedName("debit_card")
    DEBIT_CARD("Kartu Debit"),

    @SerializedName("credit_card")
    CREDIT_CARD("Kartu Credit"),

    @SerializedName("ovo")
    OVO("Ovo"),

    @SerializedName("gopay")
    GOPAY("GoPay"),

    @SerializedName("shopeepay")
    SHOPEE_PAY("Shopeepay")
}