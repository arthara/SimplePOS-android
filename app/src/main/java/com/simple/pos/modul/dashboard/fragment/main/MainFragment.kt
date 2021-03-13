package com.simple.pos.modul.dashboard.fragment.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.simple.pos.R
import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
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
        view?.findViewById<Button>(R.id.datePickerBtn)?.setOnClickListener(this)
        view?.findViewById<Button>(R.id.datePickerBtn)?.text = date
        presenter.askTopSales(date)
        presenter.askTotalSales(date)
        return view
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.datePickerBtn->{
                openDatePicker()
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

    override fun showTopSales(topSales: TopSales) {
        var productName =
                if(topSales.product == null)
                    getString(R.string.no_product_sold)
                else
                    topSales.product?.name
        var categoryName =
                if(topSales.category == null)
                    getString(R.string.no_product_sold)
                else
                    topSales.category?.name

        view?.findViewById<TextView>(R.id.topProductNameTv)?.text = productName

        view?.findViewById<TextView>(R.id.topProductTotalTv)?.text =
                getString(R.string.top_total, topSales.totalProduct)

        view?.findViewById<TextView>(R.id.topCategoryNameTv)?.text = categoryName

        view?.findViewById<TextView>(R.id.topCategoryTotalTv)?.text =
                getString(R.string.top_total, topSales.totalCategory)

        //view?.findViewById<CardView>(R.id.categoryColorInv)?.setCardBackgroundColor(topSales.category.getParsedColor())
        //view?.findViewById<Button>(R.id.categoryColorInv)?.setBackgroundColor(topSales.category?.getParsedColor()!!)
        //view?.findViewById<CardView>(R.id.categoryColorInv)?.setBackgroundColor(Color.GREEN)
    }

    override fun showTotalSales(totalSales: TotalSales) {
        view?.findViewById<TextView>(R.id.totalSalesTv)?.text =
                getString(R.string.total_sales, totalSales.totalSales)

        view?.findViewById<TextView>(R.id.grossProfitTv)?.text =
                getString(R.string.price, totalSales.grossProfit)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //add 1 to month because month start from 0
        date = getString(R.string.date, dayOfMonth, month + 1, year)

        view?.findViewById<Button>(R.id.datePickerBtn)?.text = date
        presenter.askTopSales(date)
        presenter.askTotalSales(date)
    }
}