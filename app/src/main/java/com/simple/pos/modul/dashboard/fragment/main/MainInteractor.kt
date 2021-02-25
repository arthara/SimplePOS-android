package com.simple.pos.modul.dashboard.fragment.main

import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.callback.RequestCallback

class MainInteractor: MainContract.Interactor {
    override fun requestAskTopSales(callback: RequestCallback<TopSales?>) {
        TODO("Not yet implemented")
    }

    override fun requestAskTotalSales(callback: RequestCallback<TotalSales?>) {
        TODO("Not yet implemented")
    }
}