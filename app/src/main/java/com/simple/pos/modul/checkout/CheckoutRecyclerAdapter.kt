package com.simple.pos.modul.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemProductCheckoutBinding
import com.simple.pos.shared.model.submodel.CheckoutItem
import java.lang.Exception

class CheckoutRecyclerAdapter(private val view: CheckoutContract.View,
                              private val checkoutItems: MutableCollection<CheckoutItem>)
    : RecyclerView.Adapter<CheckoutRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemProductCheckoutBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(checkoutItem: CheckoutItem) {
            binding.checkoutItem = checkoutItem
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductCheckoutBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return checkoutItems.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //find item inside by position
        checkoutItems.elementAt(position).apply {
            holder.bind(this)

            //add buttons onclick
            holder.binding.let {
                it.btnMinAll.setOnClickListener {
                    view.changeTotalItem(this, -1)
                    notifyItemChanged(position)
                }
                it.btnPlusAll.setOnClickListener {
                    view.changeTotalItem(this, 1)
                    notifyItemChanged(position)
                }
                it.deleteItemCheckoutBtn.setOnClickListener {
                    view.deleteItem(this)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount)
                }
            }
        }
    }
}
