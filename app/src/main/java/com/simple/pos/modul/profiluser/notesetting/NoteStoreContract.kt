package com.simple.pos.modul.profiluser.notesetting

import com.simple.pos.shared.callback.RequestCallback

interface NoteStoreContract {

    interface View {
        fun redirectToUpdateProduct()
        fun redirectToUserProfile()
    }

    interface Presenter {
        fun showNotes()
    }

    interface Interactor {

    }

}