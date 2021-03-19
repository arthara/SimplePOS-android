package com.simple.pos.shared.callback

interface RequestCallback<T> {
    fun requestSuccess(data: T)
    fun requestError(message: String?)
}