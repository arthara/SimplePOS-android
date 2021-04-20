package com.simple.pos.modul.dashboard.fragment.belanja

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.singletondata.ActiveCheckout

class BelanjaPresenter(private val view: BelanjaContract.View) : BelanjaContract.Presenter {
    private val interactor = BelanjaInteractor()
    private var products: Array<Product>? = null
    var filterOption: Int = 0

    override fun retrieveProducts() {
        interactor.requestRetrieveProducts(object : RequestCallback<Array<Product>?> {
            override fun requestSuccess(data: Array<Product>?) {
                if (data != null) {
                    products = data
                    showProducts()
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Error : $message")
            }
        })
    }

    override fun retrieveProductsWithCategory(id: Int) {
        interactor.requestRetrieveProductsCategory(id, object : RequestCallback<Array<Product>> {
            override fun requestSuccess(data: Array<Product>) {
                products = data
                showProducts()
            }

            override fun requestError(message: String?) {
                Log.i("NAKOERR", "$message")
            }

        })
    }

    override fun addProductToCheckout(product: Product) {
        ActiveCheckout.addNewItem(product)
    }

    override fun showProducts() {
        val productsCheckoutsId = ArrayList<Int>()

        // add product already in checkout to prevent the same product added again
        ActiveCheckout.checkout.checkoutItems.forEach {
            productsCheckoutsId.add(it.key)
        }

        products?.let {
            view.showProducts(it, productsCheckoutsId.toTypedArray())
        }
    }

    override fun chooseOption(id: Int) {
        filterOption = id
    }

}