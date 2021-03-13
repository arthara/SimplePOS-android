package com.simple.pos.modul.dashboard.fragment.belanja

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemProductStoreBinding
import com.simple.pos.shared.model.Product

class BelanjaRecyclerAdapter(private val products: Array<Product>
                             , private val view: BelanjaContract.View)
    : RecyclerView.Adapter<BelanjaRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemProductStoreBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductStoreBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
        // TODO: Add on click to shop button
    }
}