package com.simple.pos.shared.model

import com.google.gson.annotations.SerializedName

class Category(var name: String, var color: String) {
    var id: Int = -1
    @SerializedName("store_id")
    var storeId: Int = -1
}