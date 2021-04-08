package com.simple.pos.modul.profiluser.profilnote.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.callback.RetrofitCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.retrofit.ServiceGenerator

class NoteStoreUpdateInteractor: NoteStoreUpdateContract.Interactor {

    private val service = ServiceGenerator.createService(NoteStoreUpdateService::class.java)

    override fun requestUpdateStore(store: Store, callback: RequestCallback<Store?>) {
        service.updateNoteProfile(store, store.id).enqueue(
                RetrofitCallback(callback, TAG, "updateNoteOfStore")
        )
    }

    override fun requestNewUpdatedStore(callback: RequestCallback<Store?>) {
        service.retrieveStore().enqueue(RetrofitCallback(callback, TAG, "requestNewUpdatedStore"))
    }


}