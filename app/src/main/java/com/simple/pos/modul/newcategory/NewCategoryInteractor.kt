package com.simple.pos.modul.newcategory

import com.simple.pos.base.modul.BaseInteractor
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class NewCategoryInteractor : BaseInteractor<NewCategoryService>(NewCategoryService::class.java)
        , NewCategoryContract.Interactor {

    override fun requestCreateCategory(category: Category, callback: RequestCallback<Category?>){
        service.createCategory(category)
            .enqueue(
                RetrofitCallback(callback, TAG, "requestCreateCategory")
            )
    }
}