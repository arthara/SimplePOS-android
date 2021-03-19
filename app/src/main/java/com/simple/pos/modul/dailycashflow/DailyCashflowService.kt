package com.simple.pos.modul.dailycashflow

import com.simple.pos.modul.dailycashflow.model.Cashflow
import com.simple.pos.shared.apiresponse.APIResponseCollection
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

interface DailyCashflowService {
    @GET("receipts/daily/{date}")
    fun retrieveCashflows(@Path("date") date: String): Call<APIResponseCollection<Array<Cashflow>>>
}