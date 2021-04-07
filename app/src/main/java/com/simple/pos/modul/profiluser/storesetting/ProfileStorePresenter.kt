package com.simple.pos.modul.profiluser.storesetting

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.util.StoreUtil

class ProfileStorePresenter(private val view: ProfileStoreContract.View)
    : ProfileStoreContract.Presenter {

    override fun showAllStoreInfo() {
        val store = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData
        view.showStoreData(store.name, store?.address, store?.logo, store?.phoneNumber)
    }

}