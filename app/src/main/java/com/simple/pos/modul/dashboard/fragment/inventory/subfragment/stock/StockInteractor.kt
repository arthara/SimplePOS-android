package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.retrofit.ServiceGenerator

class StockInteractor: StockContract.Interactor {
    companion object {
        private val service = ServiceGenerator.createService(StockService::class.java)
    }

    override fun requestRetrieveProduct(callback: RequestCallback<Array<Product>?>){
        val call = service.retrieveProducts()

        call.enqueue(RetrofitCallback(callback, TAG, "requestRetrieveProduct"))
    }

    override fun requestDeleteProduct(product: Product, callback: RequestCallback<Void?>) {
        service.deleteProduct(product.id)
                .enqueue(RetrofitCallback(callback, TAG, "requestRetrieveProduct"))
    }
}