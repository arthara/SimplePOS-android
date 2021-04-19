package com.simple.pos.modul.editcategory

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.util.ColorUtil
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category

class EditCategoryActivity: AppCompatActivity(), EditCategoryContract.View {
    private val presenter = EditCategoryPresenter(this)
    private lateinit var category: Category
    private var mCurrentCollor: Int = 0

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

        val btn = findViewById<Button>(R.id.btnChooseColor)
        setButtonBackground(btn as AppCompatButton, category.getParsedColor())

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
        findViewById<Button>(R.id.btnChooseColor).setOnClickListener {
            Log.d("NAKOCOLOR", "${category.getParsedColor()}")
            openDialogColor(category.getParsedColor())
        }

        findViewById<ImageView>(R.id.ivBackSinglePCategory).setOnClickListener {
            finish()
        }
        
    }

    private fun openDialogColor(parsedColor: Int) {
        
        ColorPickerDialog
                .Builder(this)
                .setTitle("Pilih Warna")
                .setColorShape(ColorShape.CIRCLE)
                .setDefaultColor(parsedColor)
                .setColorListener { color, colorHex ->
                    val btn = findViewById<Button>(R.id.btnChooseColor)
                    setButtonBackground(btn as AppCompatButton, color)
                }.setPositiveButton("Terapkan").setNegativeButton("Batal").setDismissListener {
                    Log.d("ColorPickerDialog", "Handle dismiss event")
                }.show()
    }

    private fun collectInput() {
        val name = findViewById<EditText>(R.id.newCategoryNameEt).text.toString()
        val background = findViewById<Button>(R.id.btnChooseColor).background as ColorDrawable
        val hexColor = String.format("#%08X", background.color)

        category.name = name
        category.color = hexColor
    }

    private fun setButtonBackground(btn: AppCompatButton, color: Int) {

        mCurrentCollor = color
        Log.d(TAG, "NAKO: $mCurrentCollor")
        if (ColorUtil.isDarkColor(color)) {
            btn.setTextColor(Color.WHITE)
        } else {
            btn.setTextColor(Color.BLACK)
        }
        btn.setBackgroundColor(color)
    }

    override fun notifyThatListChanged() {
        // tell that create category request is successful and category list need to be refreshed
        setResult(Activity.RESULT_OK)
    }

    override fun redirectToCategoryProduct() {
        finish()
    }
}