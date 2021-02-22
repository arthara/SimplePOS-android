package com.simple.pos.modul.storeinput

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.retrofit.ServiceGenerator
import com.simple.pos.modul.storeinput.model.StoreInput
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.Companion.createFormData
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class StoreInputInteractor: StoreInputContract.Interactor{
    private val service = ServiceGenerator.createService(StoreInputService::class.java)

    override fun requestCreateStore(newStore: StoreInput, callback: RequestCallback<Store?>) {
        val name: RequestBody = newStore.name.toRequestBody()
        var logo: MultipartBody.Part? = null
        var address: RequestBody? = newStore.address?.toRequestBody()
        var phoneNumber: RequestBody? = newStore.phoneNumber?.toRequestBody()

        if(newStore.logo != null){
            // use the FileUtils to get the actual file by uri
            val file = File(newStore.logo)
            // create RequestBody instance from file
            val requestFile: RequestBody = file.asRequestBody()
            // MultipartBody.Part is used to send also the actual file name
            logo =  createFormData("logo", file.name, requestFile)
        }

        // finally, execute the request
        val call = service.create(name, logo, address, phoneNumber)
        call.enqueue(RetrofitCallback(callback, TAG, "requestCreateStore"))
    }
}