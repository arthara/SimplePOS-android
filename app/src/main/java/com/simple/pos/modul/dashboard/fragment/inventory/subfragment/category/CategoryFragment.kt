package com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simple.pos.R
import com.simple.pos.modul.editcategory.EditCategoryActivity
import com.simple.pos.modul.newcategory.NewCategoryActivity
import com.simple.pos.shared.model.Category

class CategoryFragment: Fragment(R.layout.subfragment_inventory_category), CategoryContract.View {
    private val presenter = CategoryPresenter(this)
    private var deleteDialogConfirmation: AlertDialog? = null

    companion object {
        private const val CHANGE_CATEGORY_REQ_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.retrieveCategories()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.addCategoryBtn).setOnClickListener{redirectToNewCategory()}
        presenter.showCategories()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            //refresh list when it's return ok, which means data changed
            CHANGE_CATEGORY_REQ_CODE->{
                if(resultCode == AppCompatActivity.RESULT_OK)
                    presenter.retrieveCategories()
            }
        }
    }

    override fun showCategories(categories: Array<Category>) {
        view?.findViewById<RecyclerView>(R.id.categoryListRV)?.let {
            it.adapter = CategoryRecyclerAdapter(categories, this)
        }
    }

    override fun redirectToNewCategory() {
        val intent = Intent(context, NewCategoryActivity::class.java)

        startActivityForResult(intent, CHANGE_CATEGORY_REQ_CODE)
    }

    override fun redirectToEditCategory(category: Category) {
        val bundle = Bundle()
        val intent = Intent(context, EditCategoryActivity::class.java)

        bundle.putSerializable(EditCategoryActivity.CATEGORY_BUNDLE_NAME, category)
        intent.putExtras(bundle)
        startActivityForResult(intent, CHANGE_CATEGORY_REQ_CODE)
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