package com.simple.pos.modul.dashboard.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.simple.pos.R
import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales

class MainFragment : Fragment(), MainContract.View {
    private val presenter = MainPresenter(this)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_main, container, false)
        return view
    }

    override fun showTopSales(topSales: TopSales) {
        view?.findViewById<TextView>(R.id.topProductNameTv)?.text =
                getString(R.string.top_product_name, topSales.product.name)

        view?.findViewById<TextView>(R.id.topProductTotalTv)?.text =
                getString(R.string.top_total, topSales.totalProduct)

        view?.findViewById<TextView>(R.id.topCategoryNameTv)?.text =
                getString(R.string.top_category_name, topSales.category.name)

        view?.findViewById<TextView>(R.id.topCategoryTotalTv)?.text =
                getString(R.string.top_total, topSales.totalCategory)
    }

    override fun showTotalSales(totalSales: TotalSales) {
        view?.findViewById<TextView>(R.id.totalSalesTv)?.text =
                getString(R.string.total_sales, totalSales.totalSales)

        view?.findViewById<TextView>(R.id.totalSalesTv)?.text =
                getString(R.string.gross_profit, totalSales.grossProfit)
    }
}