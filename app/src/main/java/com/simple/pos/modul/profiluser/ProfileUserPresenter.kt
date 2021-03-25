package com.simple.pos.modul.profiluser

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.model.submodel.Checkout
import com.simple.pos.shared.singletondata.ActiveCheckout
import com.simple.pos.shared.util.StoreUtil
import com.simple.pos.shared.util.TokenUtil
import com.simple.pos.shared.util.UserUtil

class ProfileUserPresenter(private val view: ProfileUserContract.View)
    : ProfileUserContract.Presenter {

    override fun showUsernameAndStorename() {
        val user = (UtilProvider.getUtil(UserUtil::class.java) as UserUtil).sessionData
        val store = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData

        view.showUserAndStoreName(user.username!!, store.name)
    }

    override fun logout() {
        (UtilProvider.getUtil(TokenUtil::class.java) as TokenUtil).destroy()
        (UtilProvider.getUtil(UserUtil::class.java) as UserUtil).destroy()
        (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).destroy()
        ActiveCheckout.resetItems()
        view.redirectToLogin()
    }
}