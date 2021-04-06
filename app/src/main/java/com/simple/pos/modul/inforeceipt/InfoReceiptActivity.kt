package com.simple.pos.modul.inforeceipt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.simple.pos.R
import com.simple.pos.databinding.ActivityInfoReceiptBinding
import com.simple.pos.shared.model.ReceiptItem
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.model.submodel.SuccessfulCheckout

class InfoReceiptActivity: AppCompatActivity(), InfoReceiptContract.View {
    private lateinit var binding: ActivityInfoReceiptBinding
    private val presenter: InfoReceiptContract.Presenter = InfoReceiptPresenter(this)

    companion object {
        const val RECEIPT_ID_BUNDLE_KEY = "RECEIPT_ID_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_info_receipt)
        intent.getIntExtra(RECEIPT_ID_BUNDLE_KEY, 0).also {
            presenter.retrieveReceipt(it)
        }
        presenter.retrieveStore()
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        binding.receiptDashboardBtn.setOnClickListener {
            redirectToDashboard()
        }
    }

    override fun redirectToDashboard() {
        finish()
    }

    override fun showReceipts(data: SuccessfulCheckout) {
        binding.checkout = data

        binding.tax = data.tax?: 0.0
        binding.subtotal = data.getSubtotal()
        showItems(data.receiptItems)
        binding.executePendingBindings()
    }

    private fun showItems(receiptItems: Array<ReceiptItem>) {
        binding.receiptListItemRv.apply {
            setHasFixedSize(true)
            adapter = InfoReceiptRecyclerAdapter(receiptItems)
        }
    }

    override fun showStore(store: Store) {
        binding.store = store

        binding.executePendingBindings()
        presenter.showLogo(store.logo)
    }

    override fun showLogo(glideUrl: GlideUrl) {
        Glide.with(this)
                .load(glideUrl)
                .into(binding.ivBusinessLogoReceipt)
    }
}