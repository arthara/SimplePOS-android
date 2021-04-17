package com.simple.pos.modul.newcategory

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContentProviderCompat.requireContext
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.util.ColorUtil
import com.github.dhaval2404.colorpicker.util.SharedPref
import com.simple.pos.R
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Category
import kotlin.properties.Delegates

class NewCategoryActivity : AppCompatActivity(), NewCategoryContract.View {
    private val presenter = NewCategoryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_category)
        initializeButtonsOnClick()
    }

    private fun initializeButtonsOnClick() {
        findViewById<Button>(R.id.inputCategoryCancelBtn).setOnClickListener {
            redirectToCategoryProduct()
        }
        findViewById<Button>(R.id.inputCategorySaveBtn).setOnClickListener {
            presenter.createCategory(collectInput())
        }
        findViewById<Button>(R.id.btnChooseColor).setOnClickListener {
            openDialogColor()
        }

        findViewById<ImageView>(R.id.ivBackSinglePCategory).setOnClickListener {
            finish()
        }

    }

    private fun openDialogColor() {

        val mDefaultColor = ""

       ColorPickerDialog
                .Builder(this)                        // Pass Activity Instance
                .setTitle("Pilih Warna")            // Default "Choose Color"
                .setColorShape(ColorShape.CIRCLE)   // Default ColorShape.CIRCLE
                .setDefaultColor(mDefaultColor)     // Pass Default Color
               .setColorListener { color, colorHex ->
                    //mColorHex = colorHex
                    val btn = findViewById<Button>(R.id.btnChooseColor)
                    setButtonBackground(btn as AppCompatButton, color)
                }.setPositiveButton("Terapkan").setNegativeButton("Batal").setDismissListener {
                    Log.d("ColorPickerDialog", "Handle dismiss event")
                }.show()
    }

    private fun setButtonBackground(btn: AppCompatButton, color: Int) {

        Log.d(TAG, "NAKO: $color")
        if (ColorUtil.isDarkColor(color)) {
            btn.setTextColor(Color.WHITE)
        } else {
            btn.setTextColor(Color.BLACK)
        }
        //btn.backgroundTintList = ColorStateList.valueOf(color)
        btn.setBackgroundColor(color)
    }


    private fun collectInput(): Category {
        val name = findViewById<EditText>(R.id.newCategoryNameEt).text.toString()
/*        val radioGroup = findViewById<RadioGroup>(R.id.inputCategoryColorRg)
        val radioButtonId = radioGroup.checkedRadioButtonId
        //convert background to colordrawable so it's possible to get the color*/
        val background = findViewById<Button>(R.id.btnChooseColor).background as ColorDrawable
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