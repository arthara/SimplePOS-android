package com.simple.pos.modul.dailycashflow

import com.simple.pos.modul.dailycashflow.model.Cashflow
import com.simple.pos.shared.apiresponse.APIResponseCollection
import com.simple.pos.shared.callback.RequestCallback

interface DailyCashflowContract {
    interface View {
        fun showDate(date: String)
        fun showCashflows(cashflows : Array<Cashflow>)
        fun redirectToMainDashboard()
    }

    interface Presenter {
        fun retrieveCashflows(date: String)
    }

    interface Interactor {
        fun requestRetrieveCashflows(date: String, requestCallback: RequestCallback<Array<Cashflow>>)
    }
}