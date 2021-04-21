package com.simple.pos.modul.dashboard.fragment.belanja

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product

class BelanjaInteractor
    : BaseInteractor<BelanjaService>(BelanjaService::class.java), BelanjaContract.Interactor {

    override fun requestRetrieveProducts(callback: RequestCallback<Array<Product>?>){
        val call = service.retrieveProducts()

        call.enqueue(RetrofitCallback(callback, TAG, "requestRetrieveProducts"))
    }
}