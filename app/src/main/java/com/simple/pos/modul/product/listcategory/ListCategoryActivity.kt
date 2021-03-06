package com.simple.pos.modul.product.listcategory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.pos.R
import com.simple.pos.databinding.ActivityListCategoriesBinding
import com.simple.pos.modul.product.create.CreateProductActivity
import com.simple.pos.modul.product.update.UpdateProductActivity

class ListCategoryActivity: AppCompatActivity(), ListCategoryContract.View, ListCategoryAdapter.ListCategoryListListener {
    private lateinit var binding : ActivityListCategoriesBinding
    private val presenter : ListCategoryContract.Presenter = ListCategoryPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_categories)
        presenter.retrieveCategoriesWithCounts()
        onClickInit()
    }

    private fun onClickInit() {
        binding.ivBackArrow.setOnClickListener{
            redirectToCreateProduct()
        }
    }

    override fun showCategories(categoryLists: Array<ListCategory>) {
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

    override fun onCardClick(listCategory: ListCategory) {
        if (intent.hasExtra("updateProduct")){
            val intent = Intent(this@ListCategoryActivity, CreateProductActivity::class.java)
            intent.putExtra(UpdateProductActivity.CATEGORY_NAME_KEY, listCategory.name)
            intent.putExtra(UpdateProductActivity.CATEGORY_ID_KEY, listCategory.id.toString())
            setResult(RESULT_OK, intent)
            finish()
        }else{
            val intent = Intent(this@ListCategoryActivity, CreateProductActivity::class.java)
            intent.putExtra(CreateProductActivity.CATEGORY_NAME_KEY, listCategory.name)
            intent.putExtra(CreateProductActivity.CATEGORY_ID_KEY, listCategory.id.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}