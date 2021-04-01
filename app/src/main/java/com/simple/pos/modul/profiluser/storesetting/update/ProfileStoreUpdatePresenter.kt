package com.simple.pos.modul.profiluser.storesetting.update

import android.text.TextUtils
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil

class ProfileStoreUpdatePresenter(private val view: ProfileStoreUpdateContract.View): ProfileStoreUpdateContract.Presenter {

    companion object {
        private val interactor = ProfileStoreUpdateInteractor()
    }

    override fun showAllStoreInfo() {
        val store = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
        view.showStoreData(store.name, store.address!!, store.phoneNumber!!)
    }

    override fun updateStore(store: Store) {
        if(!updateStoreValid(store))
            return
        interactor.requestUpdateStore(store, object: RequestCallback<Store?>{
            override fun requestSuccess(data: Store?) {
                view.updateSuccess("Berhasil mengupdate data")
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }
        })
    }

    private fun updateStoreValid(store: Store): Boolean {
        if(store.name.isEmpty())
            return false
        return true
    }

}