package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.simple.pos.R
import com.simple.pos.modul.editcategory.EditCategoryActivity
import com.simple.pos.modul.product.create.CreateProductActivity
import com.simple.pos.modul.product.update.UpdateProductActivity
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.extension.showToast
import com.simple.pos.shared.model.Product

class StockFragment: Fragment(R.layout.subfragment_inventory_stock_product), StockContract.View {
    private val presenter = StockPresenter(this)
    private var deleteDialogConfirmation: AlertDialog? = null
    private var restockDialog: AlertDialog? = null

    companion object {
        private const val CHANGE_PRODUCT_REQ_CODE = 300
        private const val CHANGE_UPDATE_PRODUCT_REQ_CODE = 800
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            //refresh list when it's return ok, which means data changed
            CHANGE_PRODUCT_REQ_CODE ->{
                if(resultCode == AppCompatActivity.RESULT_OK)
                    presenter.retrieveProducts()
            }
            CHANGE_UPDATE_PRODUCT_REQ_CODE ->{
                if(resultCode == AppCompatActivity.RESULT_OK)
                    presenter.retrieveProducts()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.retrieveProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.addProductBtn).setOnClickListener{redirectToCreateProduct()}
        presenter.showProducts()
    }

    override fun showProducts(products: Array<Product>) {
        view?.findViewById<RecyclerView>(R.id.listStockProductRv)?.let {
            val recyclerViewAdapter = StockRecyclerAdapter(products, this)

            it.adapter = recyclerViewAdapter
            view?.findViewById<TextView>(R.id.stockTotalItemTv)?.text =
                    getString(R.string.total_item, recyclerViewAdapter.itemCount)

            Log.d(TAG, "Products shown : ${products.size}}")
        }
    }

    override fun redirectToUpdateProduct(product: Product) {

        val bundle = Bundle()
        val intent = Intent(context, UpdateProductActivity::class.java)

        //bundle.putSerializable(UpdateProductActivity.PRODUCT_BUNDLE_NAME, product)
        intent.putExtras(bundle)

        startActivityForResult(intent, CHANGE_UPDATE_PRODUCT_REQ_CODE)
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

    override fun redirectToCreateProduct() {
        val intent = Intent(context, CreateProductActivity::class.java)
        startActivityForResult(intent, CHANGE_PRODUCT_REQ_CODE)
    }
}