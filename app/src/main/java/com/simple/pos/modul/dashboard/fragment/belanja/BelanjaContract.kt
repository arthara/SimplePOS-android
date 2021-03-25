package com.simple.pos.modul.dashboard.fragment.belanja

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.submodel.CheckoutItem

interface BelanjaContract {
    interface View {
        fun showProducts(products: Array<Product>)
        fun chooseProduct(product: Product)
        fun reenableChoosenProducts(productsId: Array<Int>)
    }

    interface Presenter {
        fun retrieveProducts()
        fun addProductToCheckout(product: Product)
    }

    interface Interactor {
        fun requestRetrieveProducts(callback: RequestCallback<Array<Product>?>)
    }
}