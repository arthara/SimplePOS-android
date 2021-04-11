package com.simple.pos.modul.dashboard.fragment.main

import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.callback.RequestCallback

interface MainContract {
    interface View {
        fun openDatePicker()
        fun showTopSales(topSales: TopSales)
        fun showTotalSales(totalSales: TotalSales)
        fun redirectToDailyCashflow()
    }

    interface Presenter {
        fun askTopSales(date: String)
        fun askTotalSales(date: String)
        fun showTopSales()
        fun showTotalSales()
    }

    interface Interactor {
        fun requestAskTopSales(date: String, callback: RequestCallback<TopSales?>)
        fun requestAskTotalSales(date: String, callback: RequestCallback<TotalSales?>)
    }
}