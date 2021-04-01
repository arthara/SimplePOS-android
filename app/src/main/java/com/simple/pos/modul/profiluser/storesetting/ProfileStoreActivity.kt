package com.simple.pos.modul.profiluser.storesetting

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import com.simple.pos.R
import com.simple.pos.databinding.ActivityProfileStoreBinding
import com.simple.pos.modul.dashboard.fragment.inventory.subfragment.stock.StockRecyclerAdapter
import com.simple.pos.modul.profiluser.ProfileUserActivity
import com.simple.pos.modul.profiluser.storesetting.update.ProfileStoreUpdateActivity
import com.simple.pos.shared.glide.GlideUrlUtil

class ProfileStoreActivity : AppCompatActivity(), ProfileStoreContract.View {
    private val presenter = ProfileStorePresenter(this)
    private lateinit var binding: ActivityProfileStoreBinding

    companion object {
        const val STORE_CONFIGURATION_KEY = "store key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_store)
        presenter.showAllStoreInfo()
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        findViewById<ImageView>(R.id.ivBackToProfileFromStoreConf).setOnClickListener {
            redirectToProfile()
        }
        findViewById<AppCompatButton>(R.id.goToUpdateProfileStoreBtn).setOnClickListener {
            redirectToStoreConfigurationUpdate()
        }
    }

    private fun redirectToProfile() {
        val intent = Intent(this, ProfileUserActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun showStoreData(storeName: String, address: String, logo: String, phone: String) {
        findViewById<TextView>(R.id.etStoreNameInput).text = storeName
        findViewById<TextView>(R.id.etStoreAdress).text = address
        findViewById<TextView>(R.id.etPhoneAddressView).text = phone
        loadProductImage(logo)
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