package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil

class ProfileStoreUpdatePresenter(private val view: ProfileStoreUpdateContract.View): ProfileStoreUpdateContract.Presenter {

    //private val store: Store? = null

    companion object {
        private val interactor = ProfileStoreUpdateInteractor()
        private val currentStore = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
    }

    override fun showAllStoreInfo() {
        view.showStoreData(currentStore.name, currentStore?.address, currentStore?.phoneNumber)
    }

    override fun updateStore(store: Store) {
        if(!updateStoreValid(store))
            return
        interactor.requestUpdateStore(store, object : RequestCallback<Store?> {
            override fun requestSuccess(data: Store?) {
                (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).destroy()
                (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).update(data)
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