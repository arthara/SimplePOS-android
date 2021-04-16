package com.simple.pos.modul.product.create

import android.text.TextUtils
import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
class CreateProductPresenter(private val view: CreateProductContract.View): CreateProductContract.Presenter{
    private val interactor = CreateProductInteractor()

    override fun createProduct(product: Product) {
        if (!isProductValid(product)){
            view.createFailed("Gagal mengisi data, pastikan input selain gambar terisi")
            return
        }
        normalizeInput(product)
        interactor.requestCreateProduct(product, object: RequestCallback<Product?> {
            override fun requestSuccess(data: Product?) {
                Log.d(TAG, "New Store successfully created: ${data?.toString()}")
                //show successful message or something
                view.notifyThatListChanged()
                view.createSuccess("berhasil membuat produk")
            }

            override fun requestError(message: String?) {
              view.createFailed("Gagal menyimpan data")
            }
        })
    }

    private fun normalizeInput(product: Product) {
        if(TextUtils.isEmpty(product.picture))
            product.picture = null
    }

    private fun isProductValid(product: Product): Boolean {

        if(TextUtils.isEmpty(product.name) ||
                TextUtils.isEmpty(product.categoryId.toString()) ||
                TextUtils.isEmpty(product.total.toString())  ||
                TextUtils.isEmpty(product.sellingPrice.toString())  ||
                TextUtils.isEmpty(product.costPrice.toString())
        ){
            return false
        }
        return true
    }

}