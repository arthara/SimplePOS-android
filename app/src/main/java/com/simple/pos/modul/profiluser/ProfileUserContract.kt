package com.simple.pos.modul.profiluser

import com.simple.pos.shared.model.User

interface ProfileUserContract {
    interface View {
        fun showUserAndStoreName(username: String, storeName: String)
        fun redirectToStoreConfiguration()
        fun redirectToStructConfiguration()
        fun redirectToPaymentConfiguration()
        fun redirectToLogin()
        fun redirectToDashboard()
    }

    interface Presenter {
        fun showUsernameAndStorename()
        fun logout()
    }
}