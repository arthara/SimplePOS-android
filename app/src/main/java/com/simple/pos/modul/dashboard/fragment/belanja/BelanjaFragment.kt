package com.simple.pos.modul.dashboard.fragment.belanja

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.model.submodel.CheckoutItem

class BelanjaFragment: Fragment(), BelanjaContract.View {
    private val presenter = BelanjaPresenter(this)
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_store, container, false)
        presenter.retrieveProducts()
        return view
    }

    override fun showProducts(products: Array<Product>) {
        val recyclerAdapter = BelanjaRecyclerAdapter(products, this)

        recyclerAdapter.setHasStableIds(true)
        recyclerView = view?.findViewById(R.id.listStoreProductsRv)!!
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
    }

    override fun chooseProduct(product: Product) {
        presenter.addProductToCheckout(product)
    }

    override fun reenableChoosenProducts(productsId: Array<Int>) {
        // reshow checkout button in each item
        (recyclerView.adapter!! as BelanjaRecyclerAdapter).reenableProducts(productsId)
    }
}