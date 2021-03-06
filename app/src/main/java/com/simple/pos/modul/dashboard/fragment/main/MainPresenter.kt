package com.simple.pos.modul.dashboard.fragment.main

import android.util.Log
import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG

class MainPresenter(var view: MainContract.View): MainContract.Presenter {
    private val interactor = MainInteractor()
    private var topSales: TopSales? = null
    private var totalSales: TotalSales? = null

    override fun askTopSales(date: String) {
        interactor.requestAskTopSales(date, object: RequestCallback<TopSales?> {
            override fun requestSuccess(data: TopSales?) {
                if (data != null){
                    Log.i(TAG, "Top Sales Retrieved")
                    topSales = data
                    showTopSales()
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "$message")
            }
        })
    }

    override fun askTotalSales(date: String) {
        interactor.requestAskTotalSales(date, object: RequestCallback<TotalSales?> {
            override fun requestSuccess(data: TotalSales?) {
                if (data != null){
                    Log.i(TAG, "Top Sales Retrieved")
                    totalSales = data
                    showTotalSales()
                }
            }

            override fun requestError(message: String?) {
                Log.i(TAG, "$message")
            }
        })
    }

    override fun showTopSales() {
        topSales?.let {
            view.showTopSales(it)
        }
    }

    override fun showTotalSales() {
        totalSales?.let {
            view.showTotalSales(it)
        }
    }
}