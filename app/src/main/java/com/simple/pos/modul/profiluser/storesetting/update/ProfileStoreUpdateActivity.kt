package com.simple.pos.modul.profiluser.storesetting.update

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.databinding.ActivityProfileStoreUpdateBinding
import com.simple.pos.modul.profiluser.storesetting.ProfileStoreActivity
import com.simple.pos.shared.model.Store
import com.simple.pos.shared.util.StoreUtil
import java.io.File


class ProfileStoreUpdateActivity : AppCompatActivity(), ProfileStoreUpdateContract.View {

    private val presenter = ProfileStoreUpdatePresenter(this)
    private lateinit var binding: ActivityProfileStoreUpdateBinding
    private lateinit var file: File
    private val currentStore: Store = (UtilProvider.getUtil(StoreUtil::class.java) as StoreUtil).sessionData

    companion object {
        private const val PICK_STORE_UPDATE_PHOTO_REQUEST_CODE = 2100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileStoreUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeOnClicks()
        presenter.showAllStoreInfo()
    }

    private fun initializeOnClicks() {
        binding.btnUpdateStoreUpdate.setOnClickListener {
            /* redirectToProfileStore()*/
            onClickUpdate()
        }

        binding.btnBatalStoreUpdate.setOnClickListener {
            redirectToProfileStore()
        }

        binding.ivBackToProfile.setOnClickListener {
            redirectToProfileStore()
        }

        binding.fabUploadLogoStoreUpdate.setOnClickListener{
            pickLogoFromGallery()
        }

    }

    override fun showStoreData(storeName: String, address: String?, phone: String?) {
        binding.etStoreNameInput.setText(storeName)
        binding.etStoreAdress.setText(address)
        binding.etPhoneAddressView.setText(phone)
    }

    override fun updateProfileImageSuccess(message: String, path: String?) {
        TODO("Not yet implemented")
    }

    override fun updateSuccess(message: String) {
        enableButtonOption()
        makeToast(message)
        //redirectToProfileStore()
        val intent = Intent(this, ProfileStoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun updateFailed(message: String) {
        enableButtonOption()
        makeToast(message)
    }

    private fun enableButtonOption(){
        binding.btnUpdateStoreUpdate.isEnabled = true
        binding.btnBatalStoreUpdate.isEnabled = true
    }

    override fun pickLogoFromGallery() {
        //val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, intent.type)
        startActivityForResult(intent, PICK_STORE_UPDATE_PHOTO_REQUEST_CODE)
    }

    override fun redirectToProfileStore() {
        val intent = Intent(this, ProfileStoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun onClickUpdate() {
        binding.btnUpdateStoreUpdate.isEnabled = false
        binding.btnBatalStoreUpdate.isEnabled = false
        val store = Store(binding.etStoreNameInput.text.toString()).apply {
            address = binding.etStoreAdress.text.toString()
            phoneNumber = binding.etPhoneAddressView.text.toString()
            id = currentStore.id
        }

        presenter.updateStore(store)
    }

    private fun makeToast(message: String) {
        Toast.makeText(applicationContext, message,
                Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_STORE_UPDATE_PHOTO_REQUEST_CODE) {
            if (data != null) {
                binding.ivShopLogoView.setImageURI(data.data)
            }
        }
    }

}