package com.simple.pos.storeinput

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.storeinput.model.StoreInput

interface StoreInputContract {
    interface View {
        fun redirectToHome()
        fun pickLogoFromGallery()
    }

    interface Presenter {
        fun createStore(newStore: StoreInput)
    }

    interface Interactor {
        fun requestCreateStore(newStore: StoreInput, callback: RequestCallback<Store?>)
    }
}