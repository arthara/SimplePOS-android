package com.simple.pos.modul.checkout

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.model.submodel.CheckoutItem
import com.simple.pos.shared.singletondata.ActiveCheckout
import com.simple.pos.shared.util.ExtraPayUtil
import java.lang.IllegalArgumentException

class CheckoutPresenter(private val view: CheckoutContract.View) : CheckoutContract.Presenter {

    var tax : Double = 0.0

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


        ActiveCheckout.checkout.tax = tax
        view.showBottomBarValues(subTotal, tax)
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
        if (taxNumerator > 0){
            this.tax = taxNumerator / 100
        }
    }

    override fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int) {
        try {
            ActiveCheckout.changeTotalItem(checkoutItem, addedValue)
        }catch (e: IllegalArgumentException) {
            view.showInvalidTotalItemError(checkoutItem.total)
        }
    }
}