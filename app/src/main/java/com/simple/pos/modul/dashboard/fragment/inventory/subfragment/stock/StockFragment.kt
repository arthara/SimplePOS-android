package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.extension.showToast
import com.simple.pos.shared.model.Product

class StockFragment: Fragment(), StockContract.View {
    private val presenter = StockPresenter(this)
    private var deleteDialogConfirmation: AlertDialog? = null
    private var restockDialog: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
                R.layout.subfragment_inventory_stock_product, container, false
        )

        presenter.retrieveProducts()
        return view
    }

    override fun showProducts(products: Array<Product>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.listStockProductRv)!!
        val recyclerViewAdapter = StockRecyclerAdapter(products, this)

        recyclerView.adapter = recyclerViewAdapter
        view?.findViewById<TextView>(R.id.stockTotalItemTv)!!.text =
                getString(R.string.total_item, recyclerViewAdapter.itemCount)

        Log.d(TAG, "Products shown : ${products.size}}")
    }

    override fun redirectToUpdateProduct(product: Product) {
        // TODO: add redirect
    }

    override fun showDeleteConfirmation(product: Product) {
        //create dialog the first time it's called
        if(deleteDialogConfirmation == null){
            val dialogBuilder = MaterialAlertDialogBuilder(view?.context!!)

            deleteDialogConfirmation = dialogBuilder
                    .setMessage(getString(R.string.delete_product_confirmation))
                    .setPositiveButton(R.string.no, null)
                    .setNegativeButton(R.string.yes, null)
                    .setCancelable(true)
                    .create()
        }

        deleteDialogConfirmation!!.show()
        //change yes' on click when the dialog called again
        deleteDialogConfirmation!!.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            presenter.deleteProduct(product)
            deleteDialogConfirmation!!.dismiss()
        }
    }

    override fun showRestockDialog(product: Product) {
        if (restockDialog == null) {
            view?.context?.let {
                restockDialog = MaterialAlertDialogBuilder(it)
                        .setView(layoutInflater.inflate(R.layout.popup_product_stock, null))
                        .setMessage(getString(R.string.restock_product))
                        .setPositiveButton(R.string.change_stock, null)
                        .setNegativeButton(R.string.cancel, null)
                        .setCancelable(true)
                        .create()
            }
        }

        restockDialog!!.apply {
            //reset number to 0
            findViewById<TextInputEditText>(R.id.restockTotalTextInput)?.setText(0.toString())
            show()

            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                findViewById<TextInputEditText>(R.id.restockTotalTextInput)?.let { editText ->
                    var incrementedValue: Int = editText.text.toString().toInt()

                    // if checked radio is the reduce one, change plus to minus
                    findViewById<RadioButton>(R.id.reduceStockRb)?.let {
                        if(it.isChecked)
                            incrementedValue *= (-1)
                    }

                    presenter.updateProduct(incrementedValue, product)
                    dismiss()
                }
            }
        }
    }

    override fun showNewTotalProductInvalid() {
        showToast(getString(R.string.invalid_total_product))
    }
}