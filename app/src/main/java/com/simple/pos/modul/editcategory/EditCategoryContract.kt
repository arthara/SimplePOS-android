package com.simple.pos.modul.editcategory

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category

interface EditCategoryContract {
    interface View {
        fun redirectToCategoryProduct()
        fun notifyThatListChanged()
    }

    interface Presenter {
        fun editCategory(category: Category)
    }

    interface Interactor {
        fun requestEditCategory(category: Category, callback: RequestCallback<Category?>)
    }
}