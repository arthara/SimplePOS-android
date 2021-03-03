package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class CategoryPresenter(private val view: CategoryContract.View): CategoryContract.Presenter {
    private val interactor = CategoryInteractor()

    override fun deleteCategory(category: Category) {
        interactor.requestDeleteCategory(category.id, object: RequestCallback<Unit?> {
            override fun requestSuccess(data: Unit?) {
                Log.i(TAG, "Category Is deleted successfully")
                //refresh categories
                retrieveCategories()
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "$message")
            }
        })
    }

    override fun retrieveCategories() {
        interactor.requestRetrieveCategories(object: RequestCallback<Array<Category>?> {
            override fun requestSuccess(data: Array<Category>?) {
                if (data != null) {
                    Log.i(TAG, "Total category retrieved : ${data.size}")
                    view.showCategories(data)
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "$message")
            }
        })
    }
}