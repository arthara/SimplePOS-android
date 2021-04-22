package com.simple.pos.modul.holdcheckout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.R
import com.simple.pos.base.modul.BaseRecyclerAdapter
import com.simple.pos.databinding.ItemCheckoutHoldBinding
import com.simple.pos.shared.model.HoldCheckout
import com.simple.pos.shared.util.ConverterUtil

class HoldCheckoutRecyclerAdapter(
        holdCheckouts: ArrayList<HoldCheckout>,
        private val view: HoldCheckoutContract.View
) : BaseRecyclerAdapter<HoldCheckout, HoldCheckoutRecyclerAdapter.MyViewHolder>(holdCheckouts) {

    class MyViewHolder(val binding: ItemCheckoutHoldBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(holdCheckout: HoldCheckout) {
            binding.holdCheckout = holdCheckout
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        // set sequence number
        holder.binding.tvTransactionPending.text =
                holder.itemView.resources
                        .getString(R.string.pending_transaction, position + 1)
        holder.binding.removeHoldCheckoutBtn.setOnClickListener {
            view.deleteHoldCheckout(items[position])
        }
        holder.binding.btnUnHoldCheckout.setOnClickListener {
            view.chooseHoldCheckout(items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCheckoutHoldBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }
}