package com.simple.pos.modul.login

import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Token
import com.simple.pos.shared.model.User

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {
    private val loginInteractor = LoginInteractor()

    override fun authenticate(email: String, password: String) {
        Log.d(TAG, "authenticate: { email: $email, password: $password }")
//        view.startLoading()
        loginInteractor.requestLogin(email, password, object : RequestCallback<Token?> {
            override fun requestSuccess(data: Token?) {
                saveToken(data!!)
                requestUser(data!!)
//                view.endLoading()
            }

            override fun requestError(message: String?) {
//                view.endLoading()
//                view.showError(message)
            }
        })
    }

    override fun saveToken(apiToken: Token) {
        Log.d(TAG, "saveToken: Save token to SharedPrefs")
        loginInteractor.requestSaveToken(apiToken)
    }

    override fun requestUser(apiToken: Token) {
        Log.d(TAG, "requestUser: Request user from API")
        loginInteractor.requestUser(apiToken, object : RequestCallback<User?> {
            override fun requestError(message: String?) {
//                view.showError(message)
            }

            override fun requestSuccess(data: User?) {
                if(data != null){
                    saveUser(data)
                    view.redirectToHome()
                }
            }
        })
    }

    override fun saveUser(user: User) {
        Log.d(TAG, "saveUser: Save user to SharedPrefs")
        loginInteractor.requestSaveUser(user)
    }
}