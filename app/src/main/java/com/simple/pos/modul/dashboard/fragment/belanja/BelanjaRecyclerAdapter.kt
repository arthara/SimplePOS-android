package com.simple.pos.modul.dashboard.fragment.belanja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.pos.databinding.ItemProductStoreBinding
import com.simple.pos.shared.glide.GlideUrlUtil
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

        holder.binding.let {
            loadProductImage(holder)
            //if there is no stock hide button
            if(it.product!!.total == 0)
                holder.binding.chooseProductBtn.visibility = View.GONE
            it.chooseProductBtn.setOnClickListener {
                // add product then hide button
                view.chooseProduct(products[position])
                holder.binding.chooseProductBtn.visibility = View.GONE
            }
        }
    }

    private fun loadProductImage(holder: MyViewHolder) {
        holder.binding.product!!.picture?.let {
            val imageUrl = GlideUrlUtil.convertToAuthorizedUrl(it)

            Glide.with(holder.itemView)
                .load(imageUrl)
                .into(holder.binding.ivProductItemStore)
        }
    }

    override fun getItemId(position: Int): Long {
        // use product's id as id
        return products[position].id.toLong()
    }

    // so button  doesnt go missing when recyclerview get scrolled
    override fun getItemViewType(position: Int): Int {
        return position
    }
}