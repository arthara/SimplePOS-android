package com.simple.pos.modul.dailycashflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemCashflowHarianBinding
import com.simple.pos.modul.dailycashflow.model.Cashflow

class DailyCashflowAdapter(private val cashflows: Array<Cashflow>)
    : RecyclerView.Adapter<DailyCashflowAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: ItemCashflowHarianBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(sequence: Int, cashflow: Cashflow){
            binding.let {
                it.sequence = sequence
                it.cashflow = cashflow
                it.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCashflowHarianBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cashflows.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // add 1 so the number start with 1
        holder.bind(position + 1, cashflows[position])
    }
}