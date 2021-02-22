package com.simple.pos.register

import com.simple.pos.register.submodel.RegisteringUser
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Token
import com.simple.pos.shared.retrofit.ServiceGenerator

class RegisterInteractor: RegisterContract.Interactor {
    private val service = ServiceGenerator.createService(RegisterService::class.java)

    override fun requestRegister(registeringUser: RegisteringUser, callback: RequestCallback<Token?>) {
        val call = service.register(registeringUser)

        call.enqueue(RetrofitCallback<Token>(callback, TAG, "requestRegister"))
    }
}