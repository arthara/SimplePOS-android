package com.simple.pos.modul.dashboard.fragment.main

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.simple.pos.R
import com.simple.pos.modul.dailycashflow.DailyCashflowActivity
import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment(), MainContract.View, View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private val presenter = MainPresenter(this)
    private var date = SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_main, container, false)

        view?.findViewById<Button>(R.id.datePickerBtn)?.apply {
            setOnClickListener(this@MainFragment)
            text = date
        }
        view?.findViewById<Button>(R.id.cashflowharian_fab)?.setOnClickListener(this)
        presenter.askTopSales(date)
        presenter.askTotalSales(date)
        return view
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.datePickerBtn->{
                openDatePicker()
            }
            R.id.cashflowharian_fab->{
                redirectToDailyCashflow()
            }
        }
    }

    override fun openDatePicker() {
        if(datePickerDialog == null)
            datePickerDialog = DatePickerDialog(
                    context!!,
                    this,
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
        datePickerDialog?.show()
    }

    private fun showTopCategory(category: Category?, total: Int){
        // set color to transparent if category is null
        view?.findViewById<CardView>(R.id.topCategoryColorCv)?.setCardBackgroundColor(
                category?.getParsedColor()?: Color.TRANSPARENT
        )

        // set name to no product sold if category is null
        view?.findViewById<TextView>(R.id.topCategoryNameTv)?.text =
                category?.name ?: getString(R.string.no_product_sold)

        // set total category sold
        view?.findViewById<TextView>(R.id.topCategoryTotalTv)?.text =
                getString(R.string.top_total, total)
    }

    private fun showTopProduct(product: Product?, total: Int){
        // TODO: Show product's image
        // set name to no product sold if category is null
        view?.findViewById<TextView>(R.id.topProductNameTv)?.text =
                product?.name ?: getString(R.string.no_product_sold)

        // set total product sold
        view?.findViewById<TextView>(R.id.topProductTotalTv)?.text =
                getString(R.string.top_total, total)
    }

    override fun showTopSales(topSales: TopSales) {
        showTopCategory(topSales.category, topSales.totalCategory)
        showTopProduct(topSales.product, topSales.totalProduct)
    }

    override fun showTotalSales(totalSales: TotalSales) {
        view?.findViewById<TextView>(R.id.totalSalesTv)?.text =
                getString(R.string.total_sales, totalSales.totalSales)

        view?.findViewById<TextView>(R.id.grossProfitTv)?.text =
                getString(R.string.price, totalSales.grossProfit)
    }

    override fun redirectToDailyCashflow() {
        Intent(view?.context, DailyCashflowActivity::class.java).let {
            it.putExtra(DailyCashflowActivity.DATE_BUNDLE_KEY, date)
            startActivity(it)
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //add 1 to month because month start from 0
        date = getString(R.string.date, dayOfMonth, month + 1, year)

        view?.findViewById<Button>(R.id.datePickerBtn)?.text = date
        presenter.askTopSales(date)
        presenter.askTotalSales(date)
    }
}