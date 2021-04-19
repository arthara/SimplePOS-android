package com.simple.pos.base.modul

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<ItemType, ViewHolder: RecyclerView.ViewHolder>(
        protected val items: ArrayList<ItemType>
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    protected fun removeItemAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    fun removeItem(item: ItemType) {
        if(items.remove(item))
            notifyDataSetChanged()
    }
}