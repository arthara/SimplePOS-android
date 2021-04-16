package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Product

interface StockContract {
    interface View {
        fun showProducts(products: Array<Product>)
        fun redirectToUpdateProduct(product: Product)
        fun showDeleteConfirmation(product: Product)
        fun showRestockDialog(product: Product)
        fun showNewTotalProductInvalid()
        fun redirectToCreateProduct()
    }

    interface Presenter {
        fun retrieveProducts()
        fun deleteProduct(product: Product)
        fun updateProduct(incrementValue: Int, product: Product)
        fun showProducts()
    }

    interface Interactor {
        fun requestRetrieveProduct(callback: RequestCallback<Array<Product>?>)
        fun requestDeleteProduct(product: Product, callback: RequestCallback<Void?>)
        fun requestUpdateProduct(product: Product, callback: RequestCallback<Product>)
    }
}