package com.simple.pos.modul.login

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Token
import com.simple.pos.shared.model.User

interface LoginContract {
    interface View {
        fun redirectToHome()
        fun redirectToRegister()
    }

    interface Presenter {
        fun authenticate(email: String, password: String)
        fun saveToken(apiToken: Token)
        fun requestUser(apiToken: Token)
        fun saveUser(user: User)
    }

    interface Interactor {
        fun requestLogin(email: String, password: String, callback: RequestCallback<Token?>)
        fun requestSaveToken(token: Token)
        fun requestUser(token: Token?, callback: RequestCallback<User?>)
        fun requestSaveUser(user: User?)
    }
}