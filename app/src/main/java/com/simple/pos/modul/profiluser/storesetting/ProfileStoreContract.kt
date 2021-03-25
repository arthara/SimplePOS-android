package com.simple.pos.modul.profiluser.storesetting

import com.simple.pos.shared.model.Store

interface ProfileStoreContract {

    interface View{
        fun showStoreData(storeName: String, address:String, logo:String, phone:String)
        fun redirectToStoreConfigurationUpdate();
    }

    interface Interactor{

    }

    interface Presenter{
        fun showAllStoreInfo()
    }

}