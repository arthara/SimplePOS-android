package com.simple.pos.modul.product.update

import android.text.TextUtils
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product

class UpdateProductPresenter(private val view: UpdateProductContract.View): UpdateProductContract.Presenter {

    private val interactor = UpdateProductInteractor()

    override fun updateProduct(product: Product) {

        if (!isProductValid(product)){
            view.updateFailed("Gagal mengisi data, pastikan input selain gambar terisi")
            return
        }
        normalizeInput(product)

        interactor.requestUpdateProduct(product, object: RequestCallback<Product?>{
            override fun requestSuccess(data: Product?) {
                view.notifyThatListChanged()
                view.updateSuccess("Sukses Update Produk")
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }
        })
    }

    override fun retrieveCategory(id: Int) {
        interactor.requestRetrieveCategory(id, object : RequestCallback<Category>{
            override fun requestSuccess(data: Category) {
                view.categoryInitial(data.id, data.name)
            }

            override fun requestError(message: String?) {
                view.updateFailed("gagal mengambil kategori dari produk")
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
            view.updateFailed("isi data selain gambar tidak boleh kosong")
            return false
        }
        return true
    }

}