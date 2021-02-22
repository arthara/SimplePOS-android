package com.simple.pos.storeinput

import android.text.TextUtils
import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.storeinput.model.StoreInput

class StoreInputPresenter(private val view: StoreInputContract.View): StoreInputContract.Presenter {
    private val interactor = StoreInputInteractor()

    override fun createStore(newStore: StoreInput) {
        Log.d(TAG, "Creating New Store")
        if(!validateInput(newStore))
            return
        normalizeInput(newStore)
        interactor.requestCreateStore(newStore, object: RequestCallback<Store?> {
            override fun requestSuccess(data: Store?) {
                Log.d(TAG, "New Store successfully created: ${data?.toString()}")
                //show successful message or something
//                view.endLoading()
                view.redirectToHome()
            }

            override fun requestError(message: String?) {
//                view.endLoading()
//                view.showError(message)
            }
        })
    }

    private fun normalizeInput(newStore: StoreInput) {
        //change empty string to null value
        if(TextUtils.isEmpty(newStore.address))
            newStore.address = null
        if(TextUtils.isEmpty(newStore.phoneNumber))
            newStore.phoneNumber = null
    }

    private fun validateInput(newStore: StoreInput): Boolean{
        //TODO: show error
        if(TextUtils.isEmpty(newStore.name)){
            Log.d(TAG, "Validation Fail : name = ${newStore.name}")
            return false
        }
        return true
    }
}