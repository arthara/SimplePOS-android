package com.simple.pos.modul.dailycashflow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.pos.R
import com.simple.pos.databinding.ActivityCashflowHarianBinding
import com.simple.pos.modul.dailycashflow.model.Cashflow

class DailyCashflowActivity: AppCompatActivity(), DailyCashflowContract.View {
    private lateinit var binding : ActivityCashflowHarianBinding
    private val presenter : DailyCashflowContract.Presenter = DailyCashflowPresenter(this)

    companion object {
        const val DATE_BUNDLE_KEY = "date key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val date = intent.extras?.getString(DATE_BUNDLE_KEY)!!

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cashflow_harian)
        binding.imageView8.setOnClickListener {redirectToMainDashboard()}
        showDate(date)
        presenter.retrieveCashflows(date)
    }

    override fun showDate(date: String) {
        binding.tanggalView.text = intent.extras?.getString(DATE_BUNDLE_KEY)
    }

    override fun showCashflows(cashflows: Array<Cashflow>) {
        binding.listCashflowRv.apply {
            adapter = DailyCashflowAdapter(cashflows)
            layoutManager = LinearLayoutManager(this@DailyCashflowActivity)
        }
    }

    override fun redirectToMainDashboard() {
        finish()
    }
}