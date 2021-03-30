package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.pos.databinding.ItemProductInventoryAllBinding
import com.simple.pos.shared.glide.GlideUrlUtil
import com.simple.pos.shared.model.Product

class StockRecyclerAdapter(private val products: Array<Product>
                           , private val view: StockContract.View)
    : RecyclerView.Adapter<StockRecyclerAdapter.MyViewHolder>(){

    class MyViewHolder(val binding: ItemProductInventoryAllBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductInventoryAllBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(products[position])
        loadProductImage(holder)
        holder.binding.updateProductBtn.setOnClickListener{
            view.redirectToUpdateProduct(products[position])
        }
        holder.binding.deleteProductBtn.setOnClickListener {
            view.showDeleteConfirmation(products[position])
        }
    }

    private fun loadProductImage(holder: MyViewHolder) {
        holder.binding.product!!.picture?.let {
            val imageUrl = GlideUrlUtil.convertToAuthorizedUrl(it)

            Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.binding.ivProductInv)
        }
    }
}