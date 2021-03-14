package com.simple.pos.modul.dashboard.fragment.belanja

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product

class BelanjaPresenter(private val view: BelanjaContract.View): BelanjaContract.Presenter {
    private val interactor = BelanjaInteractor()

    override fun retrieveProducts(){
        interactor.requestRetrieveProducts(object : RequestCallback<Array<Product>?>{
            override fun requestSuccess(data: Array<Product>?) {
                if(data != null)
                    view.showProducts(data)
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Error : $message")
            }
        })
    }
}