package com.simple.pos.modul.newcategory

import android.text.TextUtils
import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class NewCategoryPresenter(private val view: NewCategoryContract.View)
    : NewCategoryContract.Presenter {
    companion object {
        private val interactor = NewCategoryInteractor()
    }

    override fun createCategory(category: Category){
        if(!isCategoryValid(category))
            return
        interactor.requestCreateCategory(category, object: RequestCallback<Category?>{
            override fun requestSuccess(data: Category?) {
                view.notifyThatListChanged()
                view.redirectToCategoryProduct()
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "Failed to create new category")
            }
        })
    }

    private fun isCategoryValid(category: Category): Boolean {
        //TODO : also show error if category is invalid
        if(TextUtils.isEmpty(category.name))
            return false
        return true
    }
}