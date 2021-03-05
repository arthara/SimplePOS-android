package com.simple.pos.modul.editcategory

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import java.lang.String

class EditCategoryActivity: AppCompatActivity(), EditCategoryContract.View {
    private val presenter = EditCategoryPresenter(this)
    private lateinit var category: Category

    companion object {
        const val CATEGORY_BUNDLE_NAME = "Category Bundle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = intent.extras?.getSerializable(CATEGORY_BUNDLE_NAME) as Category
        setContentView(R.layout.activity_input_category)
        showEditedCategory()
        initializeButtonsOnClick()
    }

    private fun showEditedCategory() {
        val radioGroup = findViewById<RadioGroup>(R.id.inputCategoryColorRg)

        //compare category color with every radio color then select it if equals
        for(i in 0..radioGroup.childCount){
            val radioButton = radioGroup.getChildAt(i) as RadioButton
            val background = radioButton.background as ColorDrawable

            if(background.color == category.getParsedColor()){
                Log.d(TAG, "Color ${background.color} == ${category.getParsedColor()}")
                radioGroup.clearCheck()
                radioButton.isChecked = true
                break
            }
        }
        findViewById<EditText>(R.id.newCategoryNameEt).setText(category.name)
    }

    private fun initializeButtonsOnClick() {
        findViewById<Button>(R.id.inputCategoryCancelBtn).setOnClickListener{
            redirectToCategoryProduct()
        }
        findViewById<Button>(R.id.inputCategorySaveBtn).setOnClickListener{
            collectInput()
            presenter.editCategory(category)
        }
    }

    private fun collectInput() {
        val name = findViewById<EditText>(R.id.newCategoryNameEt).text.toString()
        val radioGroup = findViewById<RadioGroup>(R.id.inputCategoryColorRg)
        val radioButtonId = radioGroup.checkedRadioButtonId
        //convert background to colordrawable so it's possible to get the color
        val background = findViewById<RadioButton>(radioButtonId).background as ColorDrawable
        val hexColor = String.format("#%08X", background.color)

        category.name = name
        category.color = hexColor
    }

    override fun notifyThatListChanged() {
        // tell that create category request is successful and category list need to be refreshed
        setResult(Activity.RESULT_OK)
    }

    override fun redirectToCategoryProduct() {
        finish()
    }
}