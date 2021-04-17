package com.simple.pos.modul.dailycashflow

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.pos.R
import com.simple.pos.databinding.ActivityCashflowHarianBinding
import com.simple.pos.modul.dailycashflow.model.Cashflow
import com.simple.pos.modul.inforeceipt.InfoReceiptActivity

class DailyCashflowActivity: AppCompatActivity(), DailyCashflowContract.View, DailyCashflowAdapter.ListCashFlowListener {
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
        val dailyCashflowAdapter = DailyCashflowAdapter(cashflows)
        binding.listCashflowRv.apply {
            adapter = dailyCashflowAdapter.apply {
                setListDailyCashFlowClickListener(this@DailyCashflowActivity)
            }
            layoutManager = LinearLayoutManager(this@DailyCashflowActivity)
        }
    }

    override fun redirectToMainDashboard() {
        finish()
    }

    override fun onCardClick(cashflow: Cashflow) {
        val intent = Intent(this@DailyCashflowActivity, InfoReceiptActivity::class.java)
        intent.putExtra(InfoReceiptActivity.RECEIPT_ID_BUNDLE_KEY, cashflow.id)
        startActivity(intent)
    }
}