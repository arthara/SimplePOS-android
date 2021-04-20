package com.simple.pos.modul.dashboard.fragment.belanja


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.R
import com.simple.pos.modul.product.listcategory.ListCategoryActivity
import com.simple.pos.shared.model.Product

class BelanjaFragment: Fragment(R.layout.fragment_dashboard_store), BelanjaContract.View {
    private val presenter = BelanjaPresenter(this)
    private lateinit var recyclerView: RecyclerView

    private var categoryID: Int = 0
    private var categoryName: String? = null

    companion object{
        const val CATEGORY_NAME_KEY = "CATEGORY_NAME_BUNDLE_KEY"
        const val CATEGORY_ID_KEY = "CATEGORY_ID_BUNDLE_KEY"
        const val SHOP_TO_FILTER = 125
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.retrieveProducts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.listStoreProductsRv)
        view.findViewById<ConstraintLayout>(R.id.filterLayoutID).setOnClickListener{redirectToFilterCategory()}

    }

    private fun redirectToFilterCategory() {
       val intent = Intent(context, ListCategoryActivity::class.java)
        intent.putExtra("filterbelanja", SHOP_TO_FILTER)
        //startActivityForResult(intent, SHOP_TO_FILTER)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == SHOP_TO_FILTER && resultCode == AppCompatActivity.RESULT_OK){

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.showProducts()
    }

    override fun showProducts(products: Array<Product>, productCheckoutsId: Array<Int>) {
        recyclerView.let {
            it.adapter = BelanjaRecyclerAdapter(products, this).apply {
                setHasStableIds(true)
                disableProducts(productCheckoutsId)
            }
        }
    }

    fun putArguments(args: Bundle) {
        categoryName = args.getString(CATEGORY_NAME_KEY)

        if (categoryID > 0 ){
            categoryID.let { presenter.retrieveProductsWithCategory(it) }
            presenter.chooseOption(categoryID)
            view?.findViewById<TextView>(R.id.lblKategoriItemStore)?.text = "Kategori: $categoryName"
            Log.d("categoryid", "$categoryID,  $categoryName")
        }else{
            presenter.retrieveProducts()
        }
    }

    override fun chooseProduct(product: Product) {
        presenter.addProductToCheckout(product)
    }
}