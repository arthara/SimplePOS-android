package com.simple.pos.modul.holdcheckout

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.modul.LoadingDialog

class HoldCheckoutActivity
    : AppCompatActivity(R.layout.activity_checkout_hold), HoldCheckoutContract.View {
    private val presenter: HoldCheckoutContract.Presenter = HoldCheckoutPresenter(this)
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeOnClicks()
        presenter.showHoldCheckouts()
    }

    private fun initializeOnClicks() {
        findViewById<ImageView>(R.id.holdCheckoutBackBtn).setOnClickListener {
            redirectToCheckout()
        }
    }

    override fun redirectToCheckout() {
        finish()
    }

    override fun showHoldCheckouts(data: ArrayList<HoldCheckout>) {
        findViewById<RecyclerView>(R.id.holdCheckoutRv).apply {
            adapter = HoldCheckoutRecyclerAdapter(ArrayList(data), this@HoldCheckoutActivity)
        }
    }

    override fun deleteHoldCheckout(holdCheckout: HoldCheckout) {
        presenter.deleteHoldCheckout(holdCheckout)
    }

    override fun chooseHoldCheckout(holdCheckout: HoldCheckout) {
        presenter.chooseCheckout(holdCheckout)
    }

    override fun removeHoldCheckoutFromList(holdCheckout: HoldCheckout) {
        findViewById<RecyclerView>(R.id.holdCheckoutRv).adapter?.let {
            (it as HoldCheckoutRecyclerAdapter).removeItem(holdCheckout)
        }
    }

    override fun startLoading() {
        loadingDialog = LoadingDialog()

        loadingDialog.show(supportFragmentManager, TAG)
    }

    override fun stopLoading() {
        loadingDialog.dismiss()
    }
}