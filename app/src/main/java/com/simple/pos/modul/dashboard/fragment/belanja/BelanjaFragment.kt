package com.simple.pos.modul.dashboard.fragment.belanja

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

class BelanjaFragment: Fragment(), BelanjaContract.View {
    private val presenter = BelanjaPresenter(this)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_store, container, false)

        presenter.retrieveProducts()
        return view
    }

    override fun showProducts(products: Array<Product>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.listStoreProductsRv)
        val recyclerAdapter = BelanjaRecyclerAdapter(products, this)

        recyclerView?.adapter = recyclerAdapter
        recyclerView?.layoutManager = LinearLayoutManager(view?.context)
    }
}