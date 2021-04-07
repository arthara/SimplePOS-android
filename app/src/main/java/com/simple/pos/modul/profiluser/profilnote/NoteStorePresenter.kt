package com.simple.pos.modul.profiluser.profilnote

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.util.StoreUtil

class NoteStorePresenter (private val view: NoteStoreContract.View): NoteStoreContract.Presenter{
    override fun showNotes() {
        val store = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
        view.showNotes(store?.noteReceipt)
    }
}