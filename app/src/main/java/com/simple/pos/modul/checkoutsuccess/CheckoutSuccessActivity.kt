package com.simple.pos.modul.checkoutsuccess

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.R
import com.simple.pos.databinding.ActivityCheckoutDetailSuccessBinding

class CheckoutSuccessActivity: AppCompatActivity(), CheckoutSuccessContract.View {
    private var struckId: Int = -1
    private lateinit var binding: ActivityCheckoutDetailSuccessBinding

    companion object {
        const val RECEIPT_ID_BUNDLE_KEY = "Struck bundle key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutDetailSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeOnClicks()
        struckId = intent.getIntExtra(RECEIPT_ID_BUNDLE_KEY, -1)
        showStruckId()
    }

    private fun initializeOnClicks() {
        binding.btnLlihatReceipt.setOnClickListener {
            redirectToShowReceipt()
        }

        binding.dashboardBtn.setOnClickListener {
            redirectToDashboard()
        }
    }

    override fun showStruckId() {
        binding.idStruckTv.text = "$struckId"
    }

    override fun redirectToDashboard() {
        finish()
    }

    override fun redirectToShowReceipt() {
    }
}