package com.simple.pos.modul.dashboard

import android.util.Log
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.singletondata.ActiveCheckout
import com.simple.pos.shared.util.StoreUtil

class DashboardPresenter(private val view: DashboardContract.View): DashboardContract.Presenter {
    private val interactor = DashboardInteractor()

    override fun askStore() {
        interactor.requestAskStore(object: RequestCallback<Store?> {
            override fun requestSuccess(data: Store?) {
                if (data != null){
                    Log.i(TAG, "Store is exist, load main page")
                    saveStore(data)
                    view.changePageToMain()
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "$message, redirecting to store")
                view.redirectToStoreInput()
            }

        })
    }

    override fun saveStore(store: Store) {
        val storeUtil = UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil
        storeUtil.initialize(store)
    }

    override fun redirectingToCheckout() {
        if(ActiveCheckout.checkout.checkoutItems.size > 0)
            view.redirectToCheckout()
        else
            view.showNoItemInCheckoutError()
    }
}