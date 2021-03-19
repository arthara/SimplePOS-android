package com.simple.pos.shared.callback

import com.simple.pos.modul.dailycashflow.model.Cashflow

interface RequestCallback<T> {
    fun requestSuccess(data: T)
    fun requestError(message: String?)
    fun requestSuccess(data: Array<Cashflow>)
}