package com.simple.pos.modul.profiluser.profilnote

interface NoteStoreContract {

    interface View {
        fun redirectToUpdateNote()
        fun redirectToUserProfile()
        fun showNotes(notes:String?)
    }

    interface Presenter {
        fun showNotes()
    }

    interface Interactor {

    }

}