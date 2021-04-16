package com.simple.pos.modul.product.create

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.databinding.ActivityCreateProductBinding
import com.simple.pos.modul.product.listcategory.ListCategoryActivity
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.model.Product


class CreateProductActivity: AppCompatActivity(), CreateProductContract.View  {
    private val presenter = CreateProductPresenter(this)
    private var _binding: ActivityCreateProductBinding? = null
    private val binding get() = _binding!!
    private var filePath: String? = null

    companion object {
        private const val PICK_PRODUCT_PHOTO_REQUEST_CODE = 2200
        const val CATEGORY_NAME_KEY = "CATEGORY_NAME_BUNDLE_KEY"
        const val CATEGORY_ID_KEY = "CATEGORY_ID_BUNDLE_KEY"
        private const val CHANGE_PRODUCT_REQ_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCreateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickInit()
    }

    private fun onClickInit() {
        binding.btnCancelSingleProdInv.setOnClickListener{
            redirectToListProductInventory()
        }

        binding.ivBackSingleProductCreate.setOnClickListener{

        }

        binding.btnEditImageProdInv.setOnClickListener{
            pickLogoFromGallery()
        }

        binding.spinnerCategoryofProd.setOnClickListener {
            redirectToChooseCategory()
        }
        binding.btnSaveSingleProdInv.setOnClickListener{
            onClickCreate()
        }
    }

    private fun onClickCreate() {

        if(binding.spinnerCategoryofProd.text !== null){
            var product = Product(binding.etCreateProductName.text.toString()).apply {
                categoryId = intent.getIntExtra(CATEGORY_ID_KEY, 0)
                picture = filePath
                total = binding.etStokAwalSingleProd.text.toString().toInt()
                costPrice = binding.etHargaKulakanSingProd.text.toString().toDouble()
                sellingPrice = binding.etHargaJualSingProd.text.toString().toDouble()
            }
            presenter.createProduct(product)
        }
        makeToast("Semua data selain foto wajib diisi")
    }

    /********* Image ************/

    override fun pickLogoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, intent.type)
        startActivityForResult(intent, PICK_PRODUCT_PHOTO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_PRODUCT_PHOTO_REQUEST_CODE) {
            if (data != null) {

                binding.ivProductPhotoSingle.setImageURI(data.data)

                try{
                    filePath = getRealPathFromURI(data.data!!)

                    Log.d(TAG, "Choosing photo : ${data.data?.path}")
                    //binding.product!!.picture = filePath
                }catch (e: Exception){
                    Log.w(TAG, e.toString())
                }
            }
        }

        if (requestCode == CHANGE_PRODUCT_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                binding.spinnerCategoryofProd.text = intent.extras?.getString(CATEGORY_NAME_KEY)
            }
        }

    }

    // And to convert the image URI to the direct file system path of the image file
    private fun getRealPathFromURI(contentUri: Uri): String? {
        // can post image
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(
            contentUri,
            proj,  // Which columns to return
            null,  // WHERE clause; which rows to return (all rows)
            null,  // WHERE clause selection arguments (none)
            null
        ) // Order-by clause (ascending by name)
        val columnIndex: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        return cursor?.getString(columnIndex!!)
    }

    /*****Redirect*****/

    override fun redirectToChooseCategory() {
        val intent = Intent(this, ListCategoryActivity::class.java)
        startActivityForResult(intent, CHANGE_PRODUCT_REQ_CODE)
    }

    override fun redirectToListProductInventory() {
        finish()
    }

    override fun notifyThatListChanged() {
        setResult(Activity.RESULT_OK)
    }

    /******** Success Fail *********/
    override fun createSuccess(message: String) {
        makeToast("Berhasil membuat produk")
        redirectToListProductInventory()
    }

    override fun createFailed(message: String) {
        makeToast("Gagal membuat produk")
    }

    private fun makeToast(message: String) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }

}