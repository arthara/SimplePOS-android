package com.simple.pos.modul.checkout

import com.simple.pos.base.util.UtilProvider
import android.util.Log
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.model.submodel.CheckoutItem
import com.simple.pos.shared.singletondata.ActiveCheckout
import com.simple.pos.shared.util.ExtraPayUtil
import java.lang.IllegalArgumentException

class CheckoutPresenter(private val view: CheckoutContract.View) : CheckoutContract.Presenter {

    //Format Tax Percent 0.xxx
    private var tax : Double = 0.0

    override fun showCheckoutItems() {
        view.showCheckoutItems(
                ActiveCheckout.checkout.checkoutItems.values
        )
    }

    override fun removeItem(checkoutItem: CheckoutItem) {
        ActiveCheckout.removeItem(checkoutItem)
    }

    override fun resetCheckout() {
        val checkoutItems = ActiveCheckout.checkout.checkoutItems

        checkoutItems.forEach {
            (_, checkoutItem) -> view.deleteItem(checkoutItem)
        }
        view.redirectToDashboard()
    }

    override fun calculateBottomBarValues() {
        val subTotal = ActiveCheckout.calculateSubTotalItems()
        var taxFinal = ActiveCheckout.calculateTaxOfSubTotal(tax)


        //ActiveCheckout.checkout.tax = tax
        //view.showBottomBarValues(subTotal, tax)
        view.showBottomBarValues(subTotal, taxFinal)
    }

    override fun taxInitial() {
        (UtilProvider.getUtil(ExtraPayUtil::class.java) as ExtraPayUtil).sessionData?.tax.also {
            if (it != null) {
                tax = it
            }
        }
        view.showTaxNumerator(tax)
    }

    override fun setCurrentTaxPercent(taxNumerator: Double){
        this.tax = taxNumerator / 100
    }


    override fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int) {
        try {
            ActiveCheckout.changeTotalItem(checkoutItem, addedValue)
        }catch (e: IllegalArgumentException) {
            view.showInvalidTotalItemError(checkoutItem.total)
        }
    }

    override fun createHoldCheckout() {
        if(ActiveCheckout.checkout.checkoutItems.isEmpty()) {
            view.showCantHoldCheckoutWithZeroItem()
            return
        }

        CheckoutInteractor.requestCreateHoldCheckout(
                ActiveCheckout.convertToHoldCheckout(),
                object : RequestCallback<HoldCheckout>{
                    override fun requestSuccess(data: HoldCheckout) {
                        resetCheckout()
                        view.redirectToDashboard()
                    }

                    override fun requestError(message: String?) {
                        message?.let { Log.d(TAG, it) }
                    }
                }
        )
    }
}