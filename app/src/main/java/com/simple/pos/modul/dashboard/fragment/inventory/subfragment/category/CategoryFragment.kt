package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simple.pos.R
import com.simple.pos.shared.model.Category

class CategoryFragment: Fragment(), CategoryContract.View {
    private val presenter = CategoryPresenter(this)
    private var deleteDialogConfirmation: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(
                R.layout.subfragment_inventory_category, container, false
        )

        view?.findViewById<Button>(R.id.addCategoryBtn)?.setOnClickListener{redirectToNewCategory()}
        presenter.retrieveCategories()
        return view
    }

    override fun showCategories(categories: Array<Category>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.categoryListRV)!!
        val recyclerViewAdapter = CategoryRecyclerAdapter(categories, this)

        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(view?.context)
    }

    override fun redirectToNewCategory() {
    }

    override fun redirectToEditCategory() {
    }

    override fun showDeleteConfirmation(category: Category) {
        //create dialog the first time it's called
        if(deleteDialogConfirmation == null){
            val dialogBuilder = MaterialAlertDialogBuilder(view?.context!!)

            deleteDialogConfirmation = dialogBuilder
                    .setMessage(getString(R.string.delete_category_confirmation))
                    .setPositiveButton(R.string.no, null)
                    .setNegativeButton(R.string.yes, null)
                    .setCancelable(true)
                    .create()
        }

        deleteDialogConfirmation!!.show()
        //change yes' on click when the dialog called again
        deleteDialogConfirmation!!.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener{
            presenter.deleteCategory(category)
            deleteDialogConfirmation!!.dismiss()
        }
    }
}