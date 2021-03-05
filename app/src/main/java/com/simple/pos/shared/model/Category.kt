package com.simple.pos.shared.model

import android.graphics.Color
import android.util.Log
import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.extension.TAG
import java.io.Serializable

class Category(var name: String, var color: String): Serializable {
    var id: Int = -1
    @SerializedName("store_id")
    var storeId: Int = -1

    fun getParsedColor(): Int {
        try {
            return Color.parseColor(color)
        }catch (e: IllegalArgumentException){
            Log.d(TAG, "unknown color: $color")
            return Color.BLACK
        }
    }
}