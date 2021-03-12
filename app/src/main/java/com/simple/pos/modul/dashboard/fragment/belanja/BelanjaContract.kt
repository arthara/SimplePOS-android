package com.simple.pos.modul.dashboard.fragment.belanja

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Product

interface BelanjaContract {
    interface View {
        fun showProducts(products: Array<Product>)
    }

    interface Presenter {
        fun retrieveProducts()
    }

    interface Interactor {
        fun requestRetrieveProducts(callback: RequestCallback<Array<Product>?>)
    }
}