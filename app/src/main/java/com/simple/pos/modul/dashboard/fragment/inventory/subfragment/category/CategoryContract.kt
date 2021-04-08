package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Category

interface CategoryContract {
    interface View {
        fun redirectToNewCategory()
        fun redirectToEditCategory(category: Category)
        fun showCategories(categories: Array<Category>)
        fun showDeleteConfirmation(category: Category)
    }

    interface Presenter {
        fun deleteCategory(category: Category)
        fun retrieveCategories()
        fun showCategories()
    }

    interface Interactor {
        fun requestRetrieveCategories(callback: RequestCallback<Array<Category>?>)
        fun requestDeleteCategory(categoryId: Int, callback: RequestCallback<Unit?>)
    }
}