package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.retrofit.ServiceGenerator
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ProfileStoreUpdateInteractor: ProfileStoreUpdateContract.Interactor {

    private val service = ServiceGenerator.createService(ProfileStoreUpdateService::class.java)

    override fun requestUpdateStore(store: Store, callback: RequestCallback<Store?>) {
        service.updateProfile(store, store.id).enqueue(
                RetrofitCallback(callback, TAG, "requestUpdateStore")
        )
    }


    override fun requestUpdateProfileLogoInteractor(store: Store, requestCallback: RequestCallback<Store?>?) {

        var logo: MultipartBody.Part? = null

        if(store.logo != null) {
            // use the FileUtils to get the actual file by uri
            val file = File(store.logo!!)
            // create RequestBody instance from file
            val requestFile: RequestBody = file.asRequestBody()
            // MultipartBody.Part is used to send also the actual file name
            logo = MultipartBody.Part.createFormData("logo", file.name, requestFile)
        }

        service.updateImage(store.id, logo).enqueue(
                RetrofitCallback(requestCallback, TAG, "requestUpdateLogoStore")
        )
    }

    override fun requestNewUpdatedStore(callback: RequestCallback<Store?>) {
        service.retrieveStore().enqueue(RetrofitCallback(callback, TAG, "requestNewUpdatedStore"))
    }
}