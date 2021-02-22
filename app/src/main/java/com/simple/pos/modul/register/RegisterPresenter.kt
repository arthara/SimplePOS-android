package com.simple.pos.modul.register

import android.util.Log
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.modul.register.submodel.RegisteringUser
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Token
import com.simple.pos.shared.util.TokenUtil

class RegisterPresenter(private val view: RegisterContract.View): RegisterContract.Presenter {
    private val registerInteractor = RegisterInteractor()

    override fun register(registeringUser: RegisteringUser) {
        Log.d(TAG, "registering User")
        registerInteractor.requestRegister(registeringUser, object: RequestCallback<Token?> {
            override fun requestSuccess(data: Token?) {
                saveToken(data!!)
//                view.endLoading()
                view.redirectToHome()
            }

            override fun requestError(message: String?) {
//                view.endLoading()
//                view.showError(message)
            }
        })
    }

    override fun saveToken(apiToken: Token) {
        val tokenUtil = UtilProvider.getUtil(TokenUtil::class.java) as TokenUtil

        Log.d(TAG, "saveToken: Save token to SharedPrefs")
        tokenUtil.initialize(apiToken)
    }
}