package com.simple.pos.modul.profiluser.profilnote.update

import android.util.Log
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil

class NoteStoreUpdatePresenter(private val view: NoteStoreUpdateContract.View): NoteStoreUpdateContract.Presenter  {


    private val interactor = NoteStoreUpdateInteractor()

    override fun showNotes() {
        val currentStore = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
        view.showNotes(currentStore.noteReceipt)
    }

    override fun updateStore(store: Store) {
        interactor.requestUpdateStore(store, object : RequestCallback<Store?>{
            override fun requestSuccess(data: Store?) {
                saveUpdatedStore()
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }

        })
    }

    override fun saveUpdatedStore() {
        interactor.requestNewUpdatedStore(object: RequestCallback<Store?>{
            override fun requestSuccess(data: Store?) {
                if (data != null) {
                    Log.d("Nako", "data: ${data.id}, ${data.name}, " +
                            "${data.phoneNumber} "  + "${data.address} " + " ${data.noteReceipt}")
                    saveStoreSession(data)
                    view.updateSuccess("Berhasil mengupdate data: ${data.id}, ${data.name}")
                }
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }
        })
    }

    private fun saveStoreSession(store: Store) {
        val storeUtil = UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil
        storeUtil.update(store)
    }

}