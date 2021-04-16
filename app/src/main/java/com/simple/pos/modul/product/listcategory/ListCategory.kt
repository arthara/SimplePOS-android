package com.simple.pos.modul.product.listcategory

import android.graphics.Color
import android.util.Log
import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.extension.TAG

class ListCategory(var name: String, var color: String) {
    var id: Int = -1
    @SerializedName("store_id")
    var storeId: Int = -1
    @SerializedName("product_count")
    var productCount: Int = 0

    fun getParsedColor(): Int {
        try {
            return Color.parseColor(color)
        }catch (e: IllegalArgumentException){
            Log.d(TAG, "unknown color: $color")
            return Color.BLACK
        }
    }
}