package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Product

interface StockContract {
    interface View {
        fun showProducts(products: Array<Product>)
        fun redirectToUpdateProduct(product: Product)
        fun showDeleteConfirmation(product: Product)
    }

    interface Presenter {
        fun retrieveProducts()
        fun deleteProduct(product: Product)
    }

    interface Interactor {
        fun requestRetrieveProduct(callback: RequestCallback<Array<Product>?>)
        fun requestDeleteProduct(product: Product, callback: RequestCallback<Void?>)
    }
}