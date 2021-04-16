package com.simple.pos.modul.product.listcategory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.pos.R
import com.simple.pos.databinding.ActivityListCategoriesBinding
import com.simple.pos.modul.product.create.CreateProductActivity
import com.simple.pos.modul.product.listcategory.model.ListCategory
import com.simple.pos.shared.model.Category

class ListCategoryActivity: AppCompatActivity(), ListCategoryContract.View, ListCategoryAdapter.ListCategoryListListener {
    private lateinit var binding : ActivityListCategoriesBinding
    private val presenter : ListCategoryContract.Presenter = ListCategoryPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_categories)
        presenter.retrieveCategories()
        onClickInit()
    }

    private fun onClickInit() {
        binding.ivBackArrow.setOnClickListener{
            redirectToCreateProduct()
        }
    }

    override fun showCategories(categoryLists: Array<Category>) {
        val listCategoryAdapter = ListCategoryAdapter(categoryLists)
        binding.rvListCategoriesWithCount.apply {
            adapter = listCategoryAdapter.apply {
                setListCategoryListListener(this@ListCategoryActivity)
            }
            layoutManager = LinearLayoutManager(this@ListCategoryActivity)
        }
    }


    override fun redirectToCreateProduct() {
        finish()
    }

    override fun onCardClick(listCategory: Category) {
        val intent = Intent(this@ListCategoryActivity, CreateProductActivity::class.java)
        intent.putExtra(CreateProductActivity.CATEGORY_ID_KEY, listCategory.id)
        intent.putExtra(CreateProductActivity.CATEGORY_NAME_KEY, listCategory.name)
        setResult(RESULT_OK, intent);
        redirectToCreateProduct()
    }
}