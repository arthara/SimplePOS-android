package com.simple.pos.modul.dailycashflow

import com.simple.pos.modul.dailycashflow.model.Cashflow
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.retrofit.ServiceGenerator

object DailyCashflowInteractor: DailyCashflowContract.Interactor {
    private val service = ServiceGenerator.createService(DailyCashflowService::class.java)

    override fun requestRetrieveCashflows(
            date: String, requestCallback: RequestCallback<Array<Cashflow>>
    ) {
        service.retrieveCashflows(date)
                .enqueue(RetrofitCallback(
                        requestCallback, TAG, "requestRetrieveCashflows"
                ))
    }
}