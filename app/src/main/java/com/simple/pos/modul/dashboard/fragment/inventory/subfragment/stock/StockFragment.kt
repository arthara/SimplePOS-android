package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product

class StockFragment: Fragment(), StockContract.View {
    private val presenter = StockPresenter(this)
    private var deleteDialogConfirmation: AlertDialog? = null

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
        recyclerViewAdapter.notifyDataSetChanged()
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
}