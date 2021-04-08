package com.simple.pos.modul.profiluser.storesetting.update

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil

class ProfileStoreUpdatePresenter(private val view: ProfileStoreUpdateContract.View) : ProfileStoreUpdateContract.Presenter {

    //private val store: Store? = null

    private val interactor = ProfileStoreUpdateInteractor()
    private val currentStore = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData


    override fun showAllStoreInfo() {
        view.showStoreData(currentStore.name, currentStore?.address, currentStore?.phoneNumber)
    }

    override fun updateStore(store: Store) {
        if (!updateStoreValid(store))
            return
        interactor.requestUpdateStore(store, object : RequestCallback<Store?> {
            override fun requestSuccess(data: Store?) {
                saveUpdatedStore()
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }
        })
    }

    override fun saveUpdatedStore() {
        interactor.requestNewUpdatedStore(object : RequestCallback<Store?> {
            override fun requestSuccess(data: Store?) {
                if (data != null) {
                    saveStoreSession(data)
                    view.updateSuccess("Berhasil mengupdate data: ${data.id}, ${data.name}")
                }
            }

            override fun requestError(message: String?) {
                view.updateFailed("Gagal mengupdate data")
            }
        })
    }

    private fun updateStoreValid(store: Store): Boolean {
        if (store.name.isEmpty())
            return false
        return true
    }

    private fun saveStoreSession(store: Store) {
        val storeUtil = UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil
        storeUtil.update(store)
    }


}