package com.simple.pos.modul.profiluser.profilnote.update

import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store

interface NoteStoreUpdateContract {

    interface View {
        fun redirectToNoteStore()
        fun showNotes(note: String?)
        fun updateSuccess(message: String)
        fun updateFailed(message: String)
    }

    interface Presenter {
        fun showNotes()
        fun updateStore(store: Store)
    }

    interface Interactor {
        fun requestUpdateStore(store: Store, callback: RequestCallback<Store?>)
    }

}