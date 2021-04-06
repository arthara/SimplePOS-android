package com.simple.pos.modul.inforeceipt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemCheckoutReceiptBinding
import com.simple.pos.shared.model.ReceiptItem

class InfoReceiptRecyclerAdapter(private val receiptItems: Array<ReceiptItem>)
    : RecyclerView.Adapter<InfoReceiptRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemCheckoutReceiptBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(receiptItem: ReceiptItem) {
            binding.totalPrice = receiptItem.unitTotal * receiptItem.productHistory.sellingPrice
            binding.item = receiptItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckoutReceiptBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return receiptItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(receiptItems[position])
    }
}