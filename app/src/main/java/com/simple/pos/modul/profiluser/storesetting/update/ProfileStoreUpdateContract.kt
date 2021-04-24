package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import java.io.File

interface ProfileStoreUpdateContract  {

    interface View{
        fun showStoreData(storeName: String, address: String?, phone: String?, logo: String?)
        fun showStoreLogo(logo: String)
        fun updateProfileImageSuccess(message: String)
        fun updateSuccess(message: String)
        fun updateFailed(message: String)
        fun pickLogoFromGallery()
        fun redirectToProfileStore()
        fun updateProfileImageFail(message: String)
    }

    interface Presenter{
        fun showAllStoreInfo()
        fun updateStore(store: Store)
        fun updateStoreLogo(store: Store)
        fun saveUpdatedStore()
        fun showStoreLogo(logo: String)
    }

    interface  Interactor {
        fun requestUpdateStore(store: Store, callback: RequestCallback<Store?>)
        fun requestUpdateProfileLogoInteractor(store: Store, requestCallback: RequestCallback<Store?>?)
        fun requestNewUpdatedStore(callback: RequestCallback<Store?>)
    }

}