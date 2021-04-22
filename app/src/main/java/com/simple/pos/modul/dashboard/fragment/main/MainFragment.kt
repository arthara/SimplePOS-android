package com.simple.pos.modul.dashboard.fragment.main

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.simple.pos.R
import com.simple.pos.modul.dailycashflow.DailyCashflowActivity
import com.simple.pos.modul.dashboard.fragment.main.model.TopSales
import com.simple.pos.modul.dashboard.fragment.main.model.TotalSales
import com.simple.pos.shared.glide.GlideUrlUtil
import com.simple.pos.shared.model.Category
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.util.ConverterUtil
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment(R.layout.fragment_dashboard_main_fix),
        MainContract.View, View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private val presenter = MainPresenter(this)
    private var date = SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().time)
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.askTopSales(date)
        presenter.askTotalSales(date)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.datePickerBtn).apply {
            setOnClickListener(this@MainFragment)
            text = date
        }
        view.findViewById<Button>(R.id.cashflowharian_fab).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.datePickerBtn -> {
                openDatePicker()
            }
            R.id.cashflowharian_fab -> {
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
        datePickerDialog!!.setButton(DialogInterface.BUTTON_NEUTRAL, "TODAY") { dialog, which -> if (which == DialogInterface.BUTTON_NEUTRAL) {
                val cal = Calendar.getInstance()

                datePickerDialog = DatePickerDialog(
                        context!!,
                        this,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                )

                //this.date = (SimpleDateFormat("dd-MM-yyyy").format(cal.time))
                setToday((SimpleDateFormat("dd-MM-yyyy").format(cal.time)))
                datePickerDialog!!.updateDate(cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH])
            }
        }
        datePickerDialog!!.datePicker.maxDate = Date().time
        datePickerDialog?.show()
    }

    private fun setToday(today: String) {
        view?.findViewById<Button>(R.id.datePickerBtn)?.apply {
            text = today
        }
    }

    private fun showTopCategory(category: Category?, total: Int){
        // set color to transparent if category is null
        view?.findViewById<CardView>(R.id.topCategoryColorCv)?.setCardBackgroundColor(
                category?.getParsedColor() ?: Color.TRANSPARENT
        )

        // set name to no product sold if category is null
        view?.findViewById<TextView>(R.id.topCategoryNameTv)?.text =
                category?.name ?: getString(R.string.no_product_sold)

        // set total category sold
        view?.findViewById<TextView>(R.id.topCategoryTotalTv)?.text =
                getString(R.string.top_total, total)
    }

    private fun showTopProduct(product: Product?, total: Int){
        view?.apply{
            // set name to no product sold if category is null
            findViewById<TextView>(R.id.topProductNameTv)?.text =
                product?.name ?: getString(R.string.no_product_sold)

            // set total product's sold
            findViewById<TextView>(R.id.topProductTotalTv)?.text =
                getString(R.string.top_total, total)

            //load product's image
            product?.picture?.let{
                Glide.with(this)
                    .load(GlideUrlUtil.convertToAuthorizedUrl(it))
                    .into(findViewById(R.id.topProductImageIv))
            }
        }
    }

    override fun showTopSales(topSales: TopSales) {
        showTopCategory(topSales.category, topSales.totalCategory)
        showTopProduct(topSales.product, topSales.totalProduct)
    }

    override fun showTotalSales(totalSales: TotalSales) {
        view?.findViewById<TextView>(R.id.totalSalesTv)?.text =
                getString(R.string.total_sales, ConverterUtil.formatRupiahWithoutSymbol(totalSales.totalSales.toDouble()))

        view?.findViewById<TextView>(R.id.grossProfitTv)?.text =
                getString(R.string.price, ConverterUtil.formatRupiahWithoutSymbol(totalSales.grossProfit))
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