package com.simple.pos.modul.editcategory

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class EditCategoryInteractor : BaseInteractor<EditCategoryService>(EditCategoryService::class.java)
        , EditCategoryContract.Interactor {

    override fun requestEditCategory(category: Category, callback: RequestCallback<Category?>){
        service.editCategory(category, category.id)
            .enqueue(
                RetrofitCallback(callback, TAG, "requestCreateCategory")
            )
    }
}