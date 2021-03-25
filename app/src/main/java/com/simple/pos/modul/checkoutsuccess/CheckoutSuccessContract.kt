package com.simple.pos.modul.checkoutsuccess

interface CheckoutSuccessContract {
    interface View {
        fun showStruckId()
        fun redirectToDashboard()
        fun redirectToShowReceipt()
    }

    interface Presenter {
    }
}