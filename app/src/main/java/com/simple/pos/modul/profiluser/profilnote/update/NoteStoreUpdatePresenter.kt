package com.simple.pos.modul.profiluser.profilnote.update

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil

class NoteStoreUpdatePresenter(private val view: NoteStoreUpdateContract.View): NoteStoreUpdateContract.Presenter  {

    companion object {
        private val interactor = NoteStoreUpdateInteractor()
        private val currentStore = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
    }

    override fun showNotes() {
        view.showNotes(currentStore.noteReceipt)
    }

    override fun updateStore(store: Store) {
        interactor.requestUpdateStore(store, object : RequestCallback<Store?>{
            override fun requestSuccess(data: Store?) {
                view.updateSuccess("Berhasil mengupdate data")
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }

        })
    }
}