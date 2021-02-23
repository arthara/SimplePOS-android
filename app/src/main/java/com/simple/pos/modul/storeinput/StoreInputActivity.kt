package com.simple.pos.modul.storeinput

import android.Manifest
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ViewAnimator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.simple.pos.R
import com.simple.pos.databinding.ActivityStoreInputBinding
import com.simple.pos.modul.dashboard.DashboardActivity
import com.simple.pos.modul.storeinput.model.StoreInput
import com.simple.pos.shared.extension.TAG

class StoreInputActivity: AppCompatActivity(), StoreInputContract.View {
    private lateinit var binding: ActivityStoreInputBinding
    private val presenter = StoreInputPresenter(this)
    private lateinit var viewAnimator: ViewAnimator

    companion object{
        private const val PICK_PHOTO_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_input)
        binding.storeInput = StoreInput("")
        initializeButtons()
        movepage()
    }

    private fun initializeButtons() {
        binding.addLogoBtn.setOnClickListener(View.OnClickListener {
            checkStoragePermission()
        })
        binding.createStoreBtn.setOnClickListener(View.OnClickListener {
            presenter.createStore(binding.storeInput!!)
        })
    }

    private fun checkStoragePermission(){
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object: PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    Log.i(TAG, "Storage permission granted")
                    pickLogoFromGallery()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    Log.i(TAG, "Storage permission is denied permanently")
                    //TODO: Berikan pesan error yg menjelaskan bahwa storage diperlukan
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    Log.i(TAG, "Storage permission denied")
                }
            })
            .check();
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode != RESULT_OK)
            return
        when(requestCode){
            PICK_PHOTO_REQUEST_CODE->{
                try{
                    val filePath = getRealPathFromURI(data?.data!!)

                    Log.d(TAG, "Choosing photo : ${data?.data?.path}")
                    binding.storeInput?.logo = filePath
                }catch (e: Exception){
                    Log.w(TAG, e.toString())
                }
            }
        }
    }

    // And to convert the image URI to the direct file system path of the image file
    private fun getRealPathFromURI(contentUri: Uri): String? {
        // can post image
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor? = contentResolver.query(contentUri,
                proj,  // Which columns to return
                null,  // WHERE clause; which rows to return (all rows)
                null,  // WHERE clause selection arguments (none)
                null) // Order-by clause (ascending by name)
        val columnIndex: Int? = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        return cursor?.getString(columnIndex!!)
    }

    override fun redirectToHome() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun pickLogoFromGallery() {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val mimeType = arrayOf(
            "image/jpeg",
            "image/png",
            "image/svg",
            "image/jpg"
        )

        pickIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeType)
        startActivityForResult(pickIntent, PICK_PHOTO_REQUEST_CODE)
    }

    private fun movepage(){
        viewAnimator = findViewById(R.id.view_animator)
        val nextt = findViewById<ImageButton>(R.id.imageView3)
        val prev = findViewById<ImageButton>(R.id.imageView6)
        val next = findViewById<Button>(R.id.buat_toko)
        val inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        val outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        viewAnimator.setInAnimation(inAnimation)
        viewAnimator.setOutAnimation(outAnimation)
        next.setOnClickListener { viewAnimator.showNext() }
        nextt.setOnClickListener { viewAnimator.showNext() }
        prev.setOnClickListener { viewAnimator.showPrevious() }
    }
}