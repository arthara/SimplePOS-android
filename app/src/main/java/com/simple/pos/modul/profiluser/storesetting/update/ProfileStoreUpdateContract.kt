package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import java.io.File

interface ProfileStoreUpdateContract  {

    interface View{
        fun showStoreData(storeName: String, address: String?, phone: String?)
        fun updateProfileImageSuccess(message: String, path: String?)
        fun updateSuccess(message: String)
        fun updateFailed(message: String)
        fun pickLogoFromGallery()
        fun redirectToProfileStore()
    }

    interface Presenter{
        fun showAllStoreInfo()
        fun updateStore(store: Store)
        fun saveUpdatedStore()
    }

    interface  Interactor {
        fun requestUpdateStore(store: Store, callback: RequestCallback<Store?>)
        fun updateProfileImageInteractor(id: String?, imageFile: File?, requestCallback: RequestCallback<String?>?)
        fun requestNewUpdatedStore(callback: RequestCallback<Store?>)
    }

}