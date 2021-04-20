package com.simple.pos.modul.dashboard.fragment.belanja

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.retrofit.ServiceGenerator

class BelanjaInteractor: BelanjaContract.Interactor {

    private val service = ServiceGenerator.createService(BelanjaService::class.java)


    override fun requestRetrieveProducts(callback: RequestCallback<Array<Product>?>){
        val call = service.retrieveProducts()

        call.enqueue(RetrofitCallback(callback, TAG, "requestRetrieveProducts"))
    }

    override fun requestRetrieveProductsCategory(id: Int, requestCallback: RequestCallback<Array<Product>>) {
        service.retrieveProductofCategory(id).enqueue(RetrofitCallback(
                requestCallback, TAG, "requestRetrieveProductsOfCategory"
        ))
    }
}