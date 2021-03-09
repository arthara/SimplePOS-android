package com.simple.pos.modul.editcategory

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.retrofit.ServiceGenerator

class EditCategoryInteractor: EditCategoryContract.Interactor {
    companion object {
        private val service = ServiceGenerator.createService(EditCategoryService::class.java)
    }

    override fun requestEditCategory(category: Category, callback: RequestCallback<Category?>){
        service.editCategory(category, category.id)
            .enqueue(
                RetrofitCallback(callback, TAG, "requestCreateCategory")
            )
    }
}