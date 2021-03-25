package com.simple.pos.modul.detailcheckout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.databinding.ActivityCheckoutDetailBinding
import com.simple.pos.modul.checkoutsuccess.CheckoutSuccessActivity
import com.simple.pos.shared.model.PaymentMethod
import com.simple.pos.shared.model.submodel.Checkout

class DetailCheckoutActivity: AppCompatActivity(), DetailCheckoutContract.View
        , AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityCheckoutDetailBinding
    private val presenter = DetailCheckoutPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializePaymentMethods()
        initializeOnClicks()
        presenter.showCheckoutData()
    }

    private fun initializeOnClicks() {
        binding.apply {
            imageView8.setOnClickListener {
                redirectToCheckoutActivity()
            }
            saveCheckoutBtn.setOnClickListener {
                presenter.createReceipt()
            }
            paymentMethodSpinner.onItemSelectedListener = this@DetailCheckoutActivity
        }
    }

    private fun initializePaymentMethods() {
        val paymentMethods = enumValues<PaymentMethod>()
        var paymentMethodsString = ArrayList<String>()

        paymentMethods.forEach {
            paymentMethodsString.add(it.shownMethod)
        }

        binding.paymentMethodSpinner.adapter = ArrayAdapter(this
                , android.R.layout.simple_spinner_item
                , paymentMethodsString.toArray()
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    override fun startLoading() {
        //TODO: Add animation
        binding.saveCheckoutBtn.isEnabled = false
    }

    override fun stopLoading() {
        binding.saveCheckoutBtn.isEnabled = true
    }

    override fun showCheckoutData(checkout: Checkout) {
        binding.checkout = checkout
    }

    override fun redirectToCheckoutActivity() {
        finish()
    }

    override fun redirectToDetailCheckoutSuccess(receiptId: Int) {
        //set result ok to also close previous checkout activity
        setResult(RESULT_OK)

        startActivity(
            Intent(this, CheckoutSuccessActivity::class.java)
                .putExtra(CheckoutSuccessActivity.RECEIPT_ID_BUNDLE_KEY, receiptId)
        )

        finish()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(adapterView: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        presenter.changePaymentMethod(adapterView?.selectedItem.toString())
    }
}