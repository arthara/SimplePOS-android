package com.simple.pos.modul.product.create

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product

interface CreateProductContract {
    interface View{
        fun redirectToChooseCategory()
        fun redirectToListProductInventory()
        fun createSuccess(message: String)
        fun createFailed(message: String)
        fun notifyThatListChanged()
        fun pickLogoFromGallery()
    }

    interface Presenter{
        fun createProduct(product: Product)
    }

    interface Interactor{
        fun requestCreateProduct(product: Product, callback: RequestCallback<Product?>)
    }

}