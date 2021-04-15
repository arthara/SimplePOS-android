package com.simple.pos.base.modul

import com.simple.pos.shared.retrofit.ServiceGenerator

abstract class BaseInteractor<T>(private val serviceClass: Class<T> ) {
    protected val service: T get() = ServiceGenerator.createService(serviceClass)
}