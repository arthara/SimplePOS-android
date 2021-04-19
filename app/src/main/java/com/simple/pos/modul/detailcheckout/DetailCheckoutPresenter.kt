package com.simple.pos.modul.detailcheckout

import com.simple.pos.base.util.UtilProvider
import com.simple.pos.shared.callback.RequestCallback
import com.simple.pos.shared.model.PaymentMethod
import com.simple.pos.shared.model.Receipt
import com.simple.pos.shared.singletondata.ActiveCheckout
import com.simple.pos.shared.util.ExtraPayUtil
import com.simple.pos.shared.util.StoreUtil
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class DetailCheckoutPresenter(private val view: DetailCheckoutContract.View)
    : DetailCheckoutContract.Presenter {

    override fun changePaymentMethod(shownPaymentMethod: String) {
        try {
            ActiveCheckout.checkout.paymentMethod = PaymentMethod.valueOf(shownPaymentMethod)
        }catch (exception: Exception){

        }
    }

    override fun createReceipt() {
        val checkout = ActiveCheckout.checkout
        checkout.note = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData.noteReceipt

        val tax = (UtilProvider.getUtil(ExtraPayUtil::class.java) as ExtraPayUtil).sessionData?.tax

        if (tax != null)
            checkout.tax = ActiveCheckout.calculateTaxOfSubTotal(tax)

        view.startLoading()
        DetailCheckoutInteractor.requestCreateReceipt(checkout, object : RequestCallback<Receipt> {
            override fun requestSuccess(data: Receipt) {
                view.stopLoading()
                ActiveCheckout.resetCheckout()
                view.redirectToDetailCheckoutSuccess(data.id)
            }

            override fun requestError(message: String?) {
                view.stopLoading()
            }
        })
    }


    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun showCheckoutData() {
        view.showCheckoutData(ActiveCheckout.checkout)
    }
}
