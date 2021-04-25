package com.simple.pos.modul.product.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product

interface UpdateProductContract {

    interface View{
        fun redirectToChooseCategory()
        fun redirectToListProductInventory()
        fun updateSuccess(message: String)
        fun updateFailed(message: String)
        fun notifyThatListChanged()
        fun pickLogoFromGallery()
        fun categoryInitial(id: Int, name: String)
    }

    interface Presenter{
        fun updateProduct(product: Product)
        fun retrieveCategory(id: Int)
    }

    interface Interactor{
        fun requestUpdateProduct(product: Product, callback: RequestCallback<Product?>)
        fun requestRetrieveCategory(id: Int, callback: RequestCallback<Category>)
    }
}