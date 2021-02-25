package com.simple.pos.modul.dashboard

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.retrofit.ServiceGenerator

class DashboardInteractor: DashboardContract.Interactor {
    private val service = ServiceGenerator.createService(DashboardService::class.java)

    override fun requestAskStore(callback: RequestCallback<Store?>) {
        val call = service.retrieveStore()

        call.enqueue(RetrofitCallback(callback, TAG, "requestAskStore"))
    }
}