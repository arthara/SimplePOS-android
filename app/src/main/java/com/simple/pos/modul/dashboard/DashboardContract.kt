package com.simple.pos.modul.dashboard

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store

interface DashboardContract {
    interface View{
        fun changePageToMain()
        fun changePageToShopping()
        fun changePageToInventory()
        fun changePageToAccount()
        fun redirectToCheckout()
        fun redirectToStoreInput()
    }

    interface Presenter {
        fun askStore()
        fun saveStore(store: Store)
    }

    interface Interactor {
        fun requestAskStore(callback: RequestCallback<Store?>)
    }
}