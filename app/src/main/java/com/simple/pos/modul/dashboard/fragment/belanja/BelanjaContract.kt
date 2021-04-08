package com.simple.pos.modul.dashboard.fragment.belanja

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Product

interface BelanjaContract {
    interface View {
        fun showProducts(products: Array<Product>)
        fun chooseProduct(product: Product)
        fun reenableChoosenProducts(productsId: Array<Int>)
    }

    interface Presenter {
        fun retrieveProducts()
        fun addProductToCheckout(product: Product)
        fun showProducts()
    }

    interface Interactor {
        fun requestRetrieveProducts(callback: RequestCallback<Array<Product>?>)
    }
}