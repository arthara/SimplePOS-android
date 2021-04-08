package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class Store(var name: String) {
    var id = -1
    var logo: String? = null
    var address: String? = null
    @SerializedName("phone_number")
    var phoneNumber: String? = null
    @SerializedName("note_receipt")
    var noteReceipt: String? = null
    @SerializedName("user_id")
    var userId = -1
    /*var imagePath: String? = null*/

    override fun toString(): String {
        return "id = $id\n" +
                "name = $name\n" +
                "logo = $logo\n" +
                "address = $address\n" +
                "phone number = $phoneNumber\n" +
                "usersId = $userId"
    }
}