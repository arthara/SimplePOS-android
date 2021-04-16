package com.simple.pos.modul.product.listcategory

import android.util.Log
import com.simple.pos.modul.product.listcategory.model.ListCategory
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class ListCategoryPresenter(private val view: ListCategoryContract.View): ListCategoryContract.Presenter {
    private val interactor = ListCategoryInteractor()
    //private var listCategory: Array<ListCategory>? = null


    override fun retrieveCategories() {
        interactor.requestRetrieveCategories(object: RequestCallback<Array<Category>> {
            override fun requestSuccess(data: Array<Category>) {
                Log.i(TAG, "Nako : ${data.size}")
                view.showCategories(data)
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "$message")
            }
        })
    }



}