package com.simple.pos.modul.dashboard.fragment.main.model

import com.google.gson.annotations.SerializedName
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product

class TopSales(var date: String, var product: Product?, var category: Category?) {
    @SerializedName("total_product")
    var totalProduct: Int = -1
    @SerializedName("total_category")
    var totalCategory: Int = -1
}