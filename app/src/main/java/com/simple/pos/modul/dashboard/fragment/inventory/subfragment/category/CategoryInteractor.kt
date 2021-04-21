package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class CategoryInteractor
    : BaseInteractor<CategoryService>(CategoryService::class.java), CategoryContract.Interactor {

    override fun requestDeleteCategory(categoryId: Int, callback: RequestCallback<Unit?>) {
        val call = service.deleteCategory(categoryId)

        call.enqueue(
                RetrofitCallback(callback, TAG, "requestDeleteCategory")
        )
    }

    override fun requestRetrieveCategories(callback: RequestCallback<Array<Category>?>) {
        val call = service.retrieveCategories()

        call.enqueue(
                RetrofitCallback(callback, TAG, "requestRetrieveCategories")
        )
    }
}