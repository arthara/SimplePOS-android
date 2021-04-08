package com.simple.pos.modul.dashboard.fragment.belanja

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.singletondata.ActiveCheckout

class BelanjaPresenter(private val view: BelanjaContract.View): BelanjaContract.Presenter {
    private val interactor = BelanjaInteractor()
    private var products: Array<Product>? = null

    override fun retrieveProducts(){
        interactor.requestRetrieveProducts(object : RequestCallback<Array<Product>?>{
            override fun requestSuccess(data: Array<Product>?) {
                if(data != null){
                    products = data
                    showProducts()
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Error : $message")
            }
        })
    }

    override fun addProductToCheckout(product: Product) {
        ActiveCheckout.addNewItem(product)
    }

    override fun showProducts() {
        products?.let {
            view.showProducts(it)
        }
    }
}