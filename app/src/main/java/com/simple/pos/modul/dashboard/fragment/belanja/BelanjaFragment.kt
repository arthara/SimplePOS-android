package com.simple.pos.modul.dashboard.fragment.belanja

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.R
import com.simple.pos.shared.model.Product

class BelanjaFragment: Fragment(R.layout.fragment_dashboard_store), BelanjaContract.View {
    private val presenter = BelanjaPresenter(this)
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.retrieveProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.showProducts()
    }

    override fun showProducts(products: Array<Product>, productCheckoutsId: Array<Int>) {
        view?.findViewById<RecyclerView>(R.id.listStoreProductsRv)?.let {
            it.adapter = BelanjaRecyclerAdapter(products, this).apply {
                setHasStableIds(true)
                disableProducts(productCheckoutsId)
            }
        }
    }

    override fun chooseProduct(product: Product) {
        presenter.addProductToCheckout(product)
    }

    override fun reenableChoosenProducts(productsId: Array<Int>) {
        // reshow checkout button in each item
        (recyclerView.adapter!! as BelanjaRecyclerAdapter).reenableProducts(productsId)
    }
}