package com.simple.pos.modul.product.listcategory

import com.simple.pos.modul.product.listcategory.model.ListCategory
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.retrofit.ServiceGenerator

class ListCategoryInteractor: ListCategoryContract.Interactor {

    private val service = ServiceGenerator.createService(ListCategoryService::class.java)


    override fun requestRetrieveCategories(requestCallback: RequestCallback<Array<Category>>) {
        val call = service.retrieveCategories()

        call.enqueue(RetrofitCallback(requestCallback, TAG, "requestRetrieveCategories"))
    }

    override fun requestRetrieveCategoriesWithCounts(requestCallback: RequestCallback<Array<ListCategory>>) {
        val call = service.retrieveCategoriesWithCounts()

        call.enqueue(RetrofitCallback(requestCallback, TAG, "requestRetrieveCategories"))
    }

}