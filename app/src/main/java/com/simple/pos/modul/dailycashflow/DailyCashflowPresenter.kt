package com.simple.pos.modul.dailycashflow

import android.util.Log
import com.simple.pos.modul.dailycashflow.model.Cashflow
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG

class DailyCashflowPresenter(private val view: DailyCashflowContract.View)
    : DailyCashflowContract.Presenter {

    override fun retrieveCashflows(date: String) {
        DailyCashflowInteractor.requestRetrieveCashflows(
                date,
                object : RequestCallback<Array<Cashflow>>{
                    override fun requestSuccess(data: Array<Cashflow>) {
                        view.showCashflows(data)
                    }

                    override fun requestError(message: String?) {
                        Log.i(TAG, "$message")
                    }
                })
    }
}