package com.simple.pos.modul.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemProductCheckoutBinding
import com.simple.pos.shared.model.submodel.CheckoutItem

class CheckoutRecyclerAdapter(private val view: CheckoutContract.View)
    : RecyclerView.Adapter<CheckoutRecyclerAdapter.MyViewHolder>() {
    private val presenter = CheckoutRecyclerPresenter()

    class MyViewHolder(val binding: ItemProductCheckoutBinding)
        : RecyclerView.ViewHolder(binding.root), CheckoutContract.ItemView {
        override fun bind(checkoutItem: CheckoutItem) {
            binding.checkoutItem = checkoutItem
            binding.totalPrice = checkoutItem.unitTotal * checkoutItem.sellingPrice
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductCheckoutBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return presenter.checkoutItemCount
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //find item inside by position
        presenter.onBindItemViewAtPosition(position, holder)

        holder.binding.apply {
            //add buttons onclick
            checkoutItem?.let {
                val checkoutItem = it

                btnMinAll.setOnClickListener {
                    view.changeTotalItem(checkoutItem, -1)
                    notifyItemChanged(position)
                }
                btnPlusAll.setOnClickListener {
                    view.changeTotalItem(checkoutItem, 1)
                    notifyItemChanged(position)
                }
                deleteItemCheckoutBtn.setOnClickListener {
                    view.deleteItem(checkoutItem)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, itemCount)
                }
            }
        }
    }
}
