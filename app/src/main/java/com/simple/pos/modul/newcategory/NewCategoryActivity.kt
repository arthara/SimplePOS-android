package com.simple.pos.modul.newcategory

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.R
import com.simple.pos.shared.model.Category
import java.lang.String

class NewCategoryActivity: AppCompatActivity(), NewCategoryContract.View {
    private val presenter = NewCategoryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_category)
        initializeButtonsOnClick()
    }

    private fun initializeButtonsOnClick() {
        findViewById<Button>(R.id.inputCategoryCancelBtn).setOnClickListener{
            redirectToCategoryProduct()
        }
        findViewById<Button>(R.id.inputCategorySaveBtn).setOnClickListener{
            presenter.createCategory(collectInput())
        }
    }

    private fun collectInput(): Category {
        val name = findViewById<EditText>(R.id.newCategoryNameEt).text.toString()
        val radioGroup = findViewById<RadioGroup>(R.id.inputCategoryColorRg)
        val radioButtonId = radioGroup.checkedRadioButtonId
        //convert background to colordrawable so it's possible to get the color
        val background = findViewById<RadioButton>(radioButtonId).background as ColorDrawable
        val hexColor = String.format("#%08X", background.color)

        return Category(name, hexColor)
    }

    override fun notifyThatListChanged() {
        // tell that create category request is successful and category list need to be refreshed
        setResult(Activity.RESULT_OK)
    }

    override fun redirectToCategoryProduct() {
        finish()
    }
}