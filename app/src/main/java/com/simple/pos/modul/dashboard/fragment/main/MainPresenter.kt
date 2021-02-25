package com.simple.pos.modul.dashboard.fragment.main

class MainPresenter(var view: MainContract.View): MainContract.Presenter {
    private val interactor = MainInteractor()

    override fun askTopSales() {
        TODO("Not yet implemented")
    }

    override fun askTotalSales() {
        TODO("Not yet implemented")
    }
}