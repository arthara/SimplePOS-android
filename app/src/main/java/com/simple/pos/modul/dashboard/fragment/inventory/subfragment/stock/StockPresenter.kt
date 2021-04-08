package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.singletondata.ActiveCheckout

class StockPresenter(private val view: StockContract.View): StockContract.Presenter {
    private val interactor = StockInteractor()

    override fun retrieveProducts(){
        interactor.requestRetrieveProduct(object : RequestCallback<Array<Product>?> {
            override fun requestSuccess(data: Array<Product>?) {
                if(data != null){
                    ActiveCheckout.checkout.reconnectCheckoutItemWithUpdatedProducts(data)
                    view.showProducts(data)
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Error : $message")
            }
        })
    }

    override fun deleteProduct(product: Product) {
        interactor.requestDeleteProduct(product, object : RequestCallback<Void?>{
            override fun requestSuccess(data: Void?) {
                retrieveProducts()
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Error : $message")
            }
        })
    }

    override fun updateProduct(incrementValue: Int, product: Product) {
        try {
            //add or substract total product to restock
            product.total += incrementValue
        } catch (e: IllegalArgumentException) {
            view.showNewTotalProductInvalid()
            return
        }

        interactor.requestUpdateProduct(product, object : RequestCallback<Product>{
            override fun requestSuccess(data: Product) {
                //refresh list when updated successfully
                retrieveProducts()
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Error : $message")
            }

        })
    }
}