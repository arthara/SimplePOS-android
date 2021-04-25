package com.simple.pos.modul.dashboard.fragment.belanja

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.pos.R
import com.simple.pos.databinding.ItemProductStoreBinding
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.glide.GlideUrlUtil
import com.simple.pos.shared.model.Product
import com.simple.pos.shared.util.ConverterUtil

class BelanjaRecyclerAdapter(private val products: Array<Product>
                             , private val view: BelanjaContract.View)
    : RecyclerView.Adapter<BelanjaRecyclerAdapter.MyViewHolder>() {
    private val isInCheckout = BooleanArray(products.size)

    class MyViewHolder(val binding: ItemProductStoreBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) {
            binding.product = product
            //binding.sellingPrice = ConverterUtil.formatRupiahWithoutSymbol(product.sellingPrice)
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

        holder.binding.apply {
            loadProductImage(holder)
            //if in checkout hide button or otherwise
            holder.binding.chooseProductBtn.visibility =
                    if(isInCheckout[position])
                        View.GONE
                    else
                        View.VISIBLE
            //if there is no stock
            if(product!!.total == 0)
                holder.binding.chooseProductBtn.visibility = View.GONE
            chooseProductBtn.setOnClickListener {
                // add product then hide button
                view.chooseProduct(products[position])
                isInCheckout[position] = true
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
        } ?: run {
            holder.binding.ivProductItemStore.setImageResource(R.color.gray_3rd)
        }
    }

    fun disableProducts(productIds: Array<Int>) {
        productIds.forEach {
            for (i in 0..products.size) {
                if(products[i].id == it) {
                    isInCheckout[i] = true
                    Log.d(TAG, "Disable item $i")
                    notifyItemChanged(i)
                    break
                }
            }
        }
    }

    override fun getItemId(position: Int): Long {
        // use product's id as id
        return products[position].id.toLong()
    }
}