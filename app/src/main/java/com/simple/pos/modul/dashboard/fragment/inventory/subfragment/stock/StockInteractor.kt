package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product

class StockInteractor
    : BaseInteractor<StockService>(StockService::class.java), StockContract.Interactor {

    override fun requestRetrieveProduct(callback: RequestCallback<Array<Product>?>){
        val call = service.retrieveProducts()

        call.enqueue(RetrofitCallback(callback, TAG, "requestRetrieveProduct"))
    }

    override fun requestDeleteProduct(product: Product, callback: RequestCallback<Void?>) {
        service.deleteProduct(product.id)
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveProduct"))
    }

    override fun requestUpdateProduct(product: Product, callback: RequestCallback<Product>) {
        service.updateProduct(product.id, product)
                .enqueue(RetrofitCallback(callback, TAG, "requestUpdateProduct"))
    }
}