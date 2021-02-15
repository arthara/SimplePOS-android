package com.simple.pos.register

import com.simple.pos.register.submodel.RegisteringUser
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Token
import com.simple.pos.shared.model.User

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