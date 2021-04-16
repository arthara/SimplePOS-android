package com.simple.pos.modul.product.listcategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemCategoryStoreBinding


class ListCategoryAdapter(private val categoryLists: Array<ListCategory>)
    : RecyclerView.Adapter<ListCategoryAdapter.MyViewHolder>() {

    private lateinit var listCategoryListListener: ListCategoryListListener

    class MyViewHolder(val binding: ItemCategoryStoreBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(sequence: Int, listCategory: ListCategory) {
            binding.let {
                it.sequence = sequence
                it.listCategory = listCategory
                it.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryStoreBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position + 1, categoryLists[position])
        holder.binding.cvCategoryList.setOnClickListener{
            listCategoryListListener.onCardClick(categoryLists[position])
        }
    }

    override fun getItemCount(): Int {
        return categoryLists.size
    }

    interface ListCategoryListListener {
        fun onCardClick(listCategory: ListCategory)
    }

    fun setListCategoryListListener(listCategoryListListener: ListCategoryListListener) {
        this.listCategoryListListener = listCategoryListListener
    }

}