package com.simple.pos.modul.register

import com.simple.pos.modul.register.submodel.RegisteringUser
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Token

interface RegisterContract {
    interface View {
        fun redirectToLogin()
        fun redirectToHome()
    }

    interface Presenter {
        fun register(registeringUser: RegisteringUser)
        fun saveToken(apiToken: Token)
    }

    interface Interactor {
        fun requestRegister(registeringUser: RegisteringUser, callback: RequestCallback<Token?>)
    }
}