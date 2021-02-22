package com.simple.pos.modul.storeinput.model

import com.google.gson.annotations.SerializedName

class StoreInput(var name: String){
    var logo: String? = null
    var address: String? = null
    @SerializedName("phone_number")
    var phoneNumber: String? = null
}