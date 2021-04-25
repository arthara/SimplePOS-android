package com.simple.pos.modul.product.update

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.simple.pos.databinding.ActivityCreateProductBinding
import com.simple.pos.modul.product.create.CreateProductActivity
import com.simple.pos.modul.product.listcategory.ListCategoryActivity
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.glide.GlideUrlUtil
import com.simple.pos.shared.model.Product

class UpdateProductActivity : AppCompatActivity(), UpdateProductContract.View {

    private val presenter = UpdateProductPresenter(this)
    private var _binding: ActivityCreateProductBinding? = null
    private val binding get() = _binding!!
    private lateinit var product: Product
    private var filePath: String? = null
    private var categoryID: Int? = null
    private var productID: Int? = null

    companion object {
        const val PICK_PRODUCT_UPDATE_PHOTO_REQUEST_CODE = 900
        const val CATEGORY_NAME_KEY = "CATEGORY_NAME_BUNDLE_KEY"
        const val CATEGORY_ID_KEY = "CATEGORY_ID_BUNDLE_KEY"
        const val PRODUCT_BUNDLE_NAME = "Product Bundle Name"
        private const val CHANGE_PRODUCT_UPDATE_REQ_CODE = 400
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = intent.extras?.getSerializable(PRODUCT_BUNDLE_NAME) as Product
        _binding = ActivityCreateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.retrieveCategory(product.categoryId)
        productID = product.id
        Log.d("SunaoNako", "product : " + productID.toString())
        initView()
        onClickInit()
    }

    private fun initView() {

        binding.apply {
            etHargaKulakanSingProd.setText(product.costPrice.toString())
            etHargaJualSingProd.setText(product.sellingPrice.toString())
            etStokAwalSingleProd.setText(product.total.toString())
            etCreateProductName.setText(product.name)
        }
        if (product.picture != null) {
            //filePath = product.picture
            loadProductImage(product.picture!!)
        }
    }

    private fun loadProductImage(picture: String) {
        val imageUrl = GlideUrlUtil.convertToAuthorizedUrl(picture)

        Glide.with(this)
                .load(imageUrl).centerCrop()
                .into(binding.ivProductPhotoSingle)
    }

    private fun onClickInit() {
        binding.btnCancelSingleProdInv.setOnClickListener {
            redirectToListProductInventory()
        }

        binding.ivBackSingleProductCreate.setOnClickListener {
            //makeToast("Berhasil dikirim " + categoryID.toString())
            finish()
        }

        binding.btnEditImageProdInv.setOnClickListener {
            pickLogoFromGallery()
        }

        binding.spinnerCategoryofProd.setOnClickListener {
            redirectToChooseCategory()
        }
        binding.btnSaveSingleProdInv.setOnClickListener {
            onClickCreate()
        }
    }

    private fun onClickCreate() {
        if (categoryID != null|| productID != null) {
            product = Product(binding.etCreateProductName.text.toString()).apply {
                id = productID!!
                categoryId = categoryID!!
                total = binding.etStokAwalSingleProd.text.toString().toInt()
                costPrice = binding.etHargaKulakanSingProd.text.toString().toDouble()
                sellingPrice = binding.etHargaJualSingProd.text.toString().toDouble()
            }

            if (filePath != null) {
                product.picture = filePath
            }

            presenter.updateProduct(product)
            makeToast("Menunggu Pengiriman")
        } else {
            makeToast("Semua data selain foto wajib diisi")
        }
    }

    override fun redirectToChooseCategory() {
        val intent = Intent(this, ListCategoryActivity::class.java)
        intent.putExtra("updateProduct", "updateProduct")
        startActivityForResult(intent, CHANGE_PRODUCT_UPDATE_REQ_CODE)
    }

    override fun redirectToListProductInventory() {
        finish()
    }

    override fun updateSuccess(message: String) {
        makeToast(message)
        redirectToListProductInventory()
    }

    override fun updateFailed(message: String) {
        makeToast("Gagal membuat produk")
    }

    override fun notifyThatListChanged() {
        setResult(Activity.RESULT_OK)
    }

    override fun pickLogoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, intent.type)
        startActivityForResult(intent, PICK_PRODUCT_UPDATE_PHOTO_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == CreateProductActivity.PICK_PRODUCT_PHOTO_REQUEST_CODE) {
            if (data != null) {
                if(this.product.picture != null){
                    this.product.picture = null
                }
                binding.ivProductPhotoSingle.setImageURI(data.data)

                try {
                    filePath = getRealPathFromURI(data.data!!)

                    Log.d(TAG, "Choosing photo : ${data.data?.path}")
                    //binding.product!!.picture = filePath
                } catch (e: Exception) {
                    Log.w(TAG, e.toString())
                }
            }
        }

        if (requestCode == CHANGE_PRODUCT_UPDATE_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                binding.spinnerCategoryofProd.text = data?.getStringExtra(CATEGORY_NAME_KEY)
                categoryID = data?.getStringExtra(CATEGORY_ID_KEY)?.toInt()
                Log.d(TAG, "NAKO: " + data?.getStringExtra(CreateProductActivity.CATEGORY_NAME_KEY))
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


    override fun categoryInitial(id: Int, name: String) {
        binding.spinnerCategoryofProd.text = name
        categoryID = id
    }

    private fun makeToast(message: String) {
        Toast.makeText(applicationContext, message,
                Toast.LENGTH_SHORT).show()
    }

}