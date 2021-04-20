package com.simple.pos.modul.dashboard.fragment.belanja

import com.simple.pos.modul.dailycashflow.model.Cashflow
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Product

interface BelanjaContract {
    interface View {
        fun showProducts(products: Array<Product>, productCheckoutsId: Array<Int>)
        fun chooseProduct(product: Product)
    }

    interface Presenter {
        fun retrieveProducts()
        fun addProductToCheckout(product: Product)
        fun showProducts()
        fun chooseOption(id: Int)
        fun retrieveProductsWithCategory(id: Int)
    }

    interface Interactor {
        fun requestRetrieveProducts(callback: RequestCallback<Array<Product>?>)
        fun requestRetrieveProductsCategory(id: Int, requestCallback: RequestCallback<Array<Product>>)
    }
}