package com.simple.pos.modul.dashboard.fragment.main

import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.callback.RequestCallback

interface MainContract {
    interface View {
        fun showTopSales(topSales: TopSales)
        fun showTotalSales(totalSales: TotalSales)
    }

    interface Presenter {
        fun askTopSales()
        fun askTotalSales()
    }

    interface Interactor {
        fun requestAskTopSales(callback: RequestCallback<TopSales?>)
        fun requestAskTotalSales(callback: RequestCallback<TotalSales?>)
    }
}