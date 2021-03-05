package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simple.pos.databinding.ItemInventoryCategoryBinding
import com.simple.pos.shared.model.Category

class CategoryRecyclerAdapter(private val categories: Array<Category>,
                              private val view: CategoryContract.View)
    : RecyclerView.Adapter<CategoryRecyclerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemInventoryCategoryBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.binding.editCategoryBtn.setOnClickListener{
            view.redirectToEditCategory(categories[position])
        }
        holder.binding.deleteCategoryBtn.setOnClickListener {
            view.showDeleteConfirmation(categories[position])
        }
    }

    class MyViewHolder(val binding: ItemInventoryCategoryBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
        }
    }
}