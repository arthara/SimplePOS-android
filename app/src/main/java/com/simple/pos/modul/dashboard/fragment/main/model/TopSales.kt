package com.simple.pos.modul.dashboard.fragment.main.model

import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product

class TopSales(var date: String, var product: Product, var category: Category) {
    var totalProduct: Int = -1
    var totalCategory: Int = -1
}