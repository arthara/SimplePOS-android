package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class User(var email: String, var password: String) {
    var id: Int = -1
    var username: String? = null
    @SerializedName("business_name")
    var businessName: String? = null
    var photo: String? = null
}