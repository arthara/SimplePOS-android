package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.retrofit.ServiceGenerator
import retrofit2.Response

class CategoryInteractor: CategoryContract.Interactor {
    companion object{
        private val service = ServiceGenerator.createService(CategoryService::class.java)
    }

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