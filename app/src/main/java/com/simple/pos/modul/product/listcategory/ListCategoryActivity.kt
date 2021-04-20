package com.simple.pos.modul.product.listcategory

import ListCategoryContract
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.simple.pos.R
import com.simple.pos.databinding.ActivityListCategoriesBinding
import com.simple.pos.modul.dashboard.fragment.belanja.BelanjaFragment
import com.simple.pos.modul.product.create.CreateProductActivity


class ListCategoryActivity: AppCompatActivity(), ListCategoryContract.View, ListCategoryAdapter.ListCategoryListListener {
    private lateinit var binding : ActivityListCategoriesBinding
    private val presenter : ListCategoryContract.Presenter = ListCategoryPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_categories)
        if(intent.extras?.getInt("filterbelanja") != null){
            binding.tvResetCategoryStore.apply {
                isVisible = true
                setOnClickListener {
                    redirectToReset()
                }
            }
        }
        presenter.retrieveCategoriesWithCounts()
        onClickInit()
    }

    private fun redirectToReset() {
        if (intent.hasExtra("filterbelanja")){
            val resetValue = 0
            val bundle = Bundle()
            bundle.putString(BelanjaFragment.CATEGORY_ID_KEY, resetValue.toString())
            bundle.putString(BelanjaFragment.CATEGORY_NAME_KEY, "Semua")

            val belanjaFragment = BelanjaFragment()
            belanjaFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, belanjaFragment)
                    .commit()
            finish()
        }
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
        if (intent.hasExtra("filterbelanja")){
            val bundle = Bundle()
            bundle.putString(BelanjaFragment.CATEGORY_ID_KEY, listCategory.id.toString())
            bundle.putString(BelanjaFragment.CATEGORY_NAME_KEY, listCategory.name)
            val belanjaFragment = BelanjaFragment()
            belanjaFragment.putArguments(bundle)

            setResult(RESULT_OK)
            finish()
        }else{
            val intent = Intent(this@ListCategoryActivity, CreateProductActivity::class.java)
            intent.putExtra(CreateProductActivity.CATEGORY_NAME_KEY, listCategory.name)
            intent.putExtra(CreateProductActivity.CATEGORY_ID_KEY, listCategory.id.toString())
            setResult(RESULT_OK, intent);
            finish()
        }
    }
}