package com.simple.pos.modul.newcategory

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category

interface NewCategoryContract {
    interface View {
        fun redirectToCategoryProduct()
        fun notifyThatListChanged()
    }

    interface Presenter {
        fun createCategory(category: Category)
    }

    interface Interactor {
        fun requestCreateCategory(category: Category, callback: RequestCallback<Category?>)
    }
}