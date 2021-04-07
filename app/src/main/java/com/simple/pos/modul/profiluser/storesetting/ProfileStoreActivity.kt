package com.simple.pos.modul.profiluser.storesetting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.simple.pos.databinding.ActivityProfileStoreBinding
import com.simple.pos.modul.profiluser.ProfileUserActivity
import com.simple.pos.modul.profiluser.storesetting.update.ProfileStoreUpdateActivity
import com.simple.pos.shared.glide.GlideUrlUtil

class ProfileStoreActivity : AppCompatActivity(), ProfileStoreContract.View {
    private val presenter = ProfileStorePresenter(this)
    private var _binding: ActivityProfileStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.showAllStoreInfo()
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        binding.ivBackToProfileFromStoreConf.setOnClickListener {
            redirectToProfile()
        }
        binding.goToUpdateProfileStoreBtn.setOnClickListener {
            redirectToStoreConfigurationUpdate()
        }
    }

    private fun redirectToProfile() {
/*        val intent = Intent(this, ProfileUserActivity::class.java)
        startActivity(intent)*/
        finish()
    }

    override fun showStoreData(storeName: String, address: String?, logo: String?, phone: String?) {
        binding.tvStoreNameInput.text = storeName
        binding.tvStoreAddress.text = address
        binding.tvPhoneAddressInput.text = phone
        if (logo != null) {
            loadProductImage(logo)
        }
    }

    override fun redirectToStoreConfigurationUpdate() {
        val intent = Intent(this, ProfileStoreUpdateActivity::class.java)
        startActivity(intent)
    }

    private fun loadProductImage(logo: String) {
        if(!logo.isEmpty()){
            val imageUrl = GlideUrlUtil.convertToAuthorizedUrl(logo)
            Glide.with(this).load(imageUrl).centerCrop().into(binding.ivShopLogoView)
        }
    }

}