package com.simple.pos.modul.newcategory

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.retrofit.ServiceGenerator

class NewCategoryInteractor: NewCategoryContract.Interactor {
    companion object {
        private val service = ServiceGenerator.createService(NewCategoryService::class.java)
    }

    override fun requestCreateCategory(category: Category, callback: RequestCallback<Category?>){
        service.createCategory(category)
            .enqueue(
                RetrofitCallback(callback, TAG, "requestCreateCategory")
            )
    }
}