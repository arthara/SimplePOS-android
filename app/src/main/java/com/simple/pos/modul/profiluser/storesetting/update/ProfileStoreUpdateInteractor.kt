package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.retrofit.ServiceGenerator
import java.io.File

class ProfileStoreUpdateInteractor: ProfileStoreUpdateContract.Interactor {

    companion object{
        private val service = ServiceGenerator.createService(ProfileStoreUpdateService::class.java)
    }

    override fun requestUpdateStore(store: Store, callback: RequestCallback<Store?>) {
        service.updateProfile(store, store.id).enqueue(
                RetrofitCallback(callback, TAG, "requestUpdateStore")
        )
    }

    override fun updateProfileImageInteractor(id: String?, imageFile: File?, requestCallback: RequestCallback<String?>?) {
        TODO("Not yet implemented")
    }
}