package com.simple.pos.modul.dashboard.fragment.main

import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.retrofit.ServiceGenerator

class MainInteractor: MainContract.Interactor {
    private val service = ServiceGenerator.createService(MainService::class.java)

    override fun requestAskTopSales(date: String, callback: RequestCallback<TopSales?>) {
        val call = service.retrieveTopSales(date)

        call.enqueue(RetrofitCallback(callback, TAG, "requestAskTopSales"))
    }

    override fun requestAskTotalSales(date: String, callback: RequestCallback<TotalSales?>) {
        val call = service.retrieveTotalSales(date)

        call.enqueue(RetrofitCallback(callback, TAG, "requestAskTotalSales"))
    }
}