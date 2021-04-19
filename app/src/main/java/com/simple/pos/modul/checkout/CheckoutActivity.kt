package com.simple.pos.modul.checkout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.simple.pos.R
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.databinding.ActivityCheckoutBinding
import com.simple.pos.modul.detailcheckout.DetailCheckoutActivity
import com.simple.pos.modul.holdcheckout.HoldCheckoutActivity
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.extension.showToast
import com.simple.pos.shared.model.submodel.CheckoutItem
import com.simple.pos.shared.model.submodel.ExtraPay
import com.simple.pos.shared.util.ExtraPayUtil
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity: AppCompatActivity(), CheckoutContract.View {
    private val presenter: CheckoutContract.Presenter = CheckoutPresenter(this)
    private lateinit var binding: ActivityCheckoutBinding
    private val removedItems = ArrayList<Int>()
    private var taxDialog: AlertDialog? = null


    companion object {
        private const val DETAIL_CHECKOUT_REQ_CODE = 100
        const val REMOVED_CHECKOUT_ITEM_BUNDLE_KEY = "removed checkout item key"
        const val RESULT_CHECKOUT_SUCCESSFULLY = 32
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeOnClicks()
    }

    override fun onResume() {
        super.onResume()
        presenter.showCheckoutItems()
        presenter.calculateBottomBarValues()
        initializeOnClicks()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            DETAIL_CHECKOUT_REQ_CODE -> {
                //if checkout finished successfully, close activity and reset shopping view
                if(resultCode == RESULT_OK) {
                    setResult(RESULT_CHECKOUT_SUCCESSFULLY)
                    finish()
                }
            }
        }
    }

    override fun showBottomBarValues(subTotal: Double, tax: Double) {
        binding.let {
            it.subTotal = subTotal
            it.tax = tax
            it.total = subTotal - tax
        }
    }

    private fun initializeOnClicks() {
        binding.let {
            it.ivBackArrow.setOnClickListener {
                redirectToDashboard()
            }
            it.tvHoldCheckout.setOnClickListener {
                //TODO: add logic for holding checkout
            }
            it.tvResetCheckout.setOnClickListener {
                presenter.resetCheckout()
            }
            it.btnCetakCheckout.setOnClickListener {
                redirectToCheckoutDetail()
            }
        }
    }

    override fun finish() {
        // if any item get removed
        if(removedItems.size > 0)
        // put all removed item id as intent data
            setResult(RESULT_OK, Intent().putExtra(
                    REMOVED_CHECKOUT_ITEM_BUNDLE_KEY,
                    removedItems.toIntArray().also{
                        Log.d(TAG, "Remove Items : ${Arrays.toString(it)}")
                    }
            ))
        super.finish()
    }

    override fun showCheckoutItems(checkoutItems: MutableCollection<CheckoutItem>) {
        binding.checkoutItemsRv.let {
            it.adapter = CheckoutRecyclerAdapter(this, checkoutItems)
            it.layoutManager = LinearLayoutManager(this)
        }
        checkIfButtonShouldBeDisabled()
    }

    override fun deleteItem(checkoutItem: CheckoutItem) {
        removedItems.add(checkoutItem.id)
        presenter.removeItem(checkoutItem)
        presenter.calculateBottomBarValues()
        checkIfButtonShouldBeDisabled()
    }

    private fun checkIfButtonShouldBeDisabled() {
        //disable/enable checkout buton based on checkoutItems size
        binding.checkoutItemsRv.adapter?.let {
            binding.btnCetakCheckout.isEnabled =  (it.itemCount > 0)
        }
    }

    override fun redirectToDashboard() {
        finish()
    }

    override fun showInvalidTotalItemError(maxTotal: Int) {
        showToast(getString(R.string.invalid_unit_total, maxTotal))
    }

    override fun addTaxValue() {
        if (taxDialog == null) {
            taxDialog = MaterialAlertDialogBuilder(this)
                    .setView(R.layout.popup_add_tax_percent)
                    .setMessage(getString(R.string.restock_product))
                    .setPositiveButton("Apply", null)
                    .setNegativeButton(R.string.cancel, null)
                    .setCancelable(true)
                    .create()
        }

        taxDialog!!.apply {
            //reset number to 0
            findViewById<TextInputEditText>(R.id.addTaxPopup)?.setText(0.toString())
            show()

            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                findViewById<TextInputEditText>(R.id.addTaxPopup)?.let { editText ->
                    if(!editText.text.isNullOrEmpty()){
                        val taxNumerator: Double = editText.text.toString().toDouble()
                        if (taxNumerator in 0.0..100.0){
                            val extraPay = ExtraPay()
                            extraPay.tax = taxNumerator


                            //Tax in numerator value
                            (UtilProvider.getUtil(ExtraPayUtil::class.java) as ExtraPayUtil).update(extraPay)

                            binding.tvTaxPercentCheckout.text = getString(R.string.tax_numerator_label, taxNumerator.toInt())
                            presenter.setCurrentTaxPercent(taxNumerator)
                            presenter.calculateBottomBarValues()
                            taxDialog!!.dismiss()
                        }else{
                            showToast("Pastikan input yang dimasukan antara 0 dan 100")
                        }
                    }
                }
            }
        }
    }

    override fun showTaxNumerator(tax: Double) {
        binding.tvTaxPercentCheckout.text =  getString(R.string.tax_numerator_label, tax.toInt())
        presenter.setCurrentTaxPercent(tax)
    }

    override fun redirectToCheckoutDetail() {
        startActivityForResult(
                Intent(this, DetailCheckoutActivity::class.java),
                DETAIL_CHECKOUT_REQ_CODE
        )
    }

    override fun redirectToHoldCheckout() {
        startActivity(
                Intent(this, HoldCheckoutActivity::class.java)
        )
    }

    override fun showCantHoldCheckoutWithZeroItem() {
        showToast(getString(R.string.cant_hold_checkout_with_zero_item))
    }

    override fun changeTotalItem(checkoutItem: CheckoutItem, addedValue: Int) {
        // change total item and bottom bar values
        presenter.changeTotalItem(checkoutItem, addedValue)
        presenter.calculateBottomBarValues()
    }
}