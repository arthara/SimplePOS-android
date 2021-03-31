package com.simple.pos.modul.profiluser.storesetting

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.simple.pos.R
import com.simple.pos.modul.profiluser.ProfileUserActivity
import com.simple.pos.shared.model.Store

class ProfileStoreActivity: AppCompatActivity(), ProfileStoreContract.View {


    companion object {
        const val STORE_CONFIGURATION_KEY = "store key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_store)
        initializeOnClicks()
    }

    private fun initializeOnClicks() {
        findViewById<ImageView>(R.id.ivBackToProfile).setOnClickListener{
            redirectToProfile()
        }
        findViewById<AppCompatButton>(R.id.appCompatButton).setOnClickListener {
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
    }

    override fun redirectToStoreConfigurationUpdate() {

    }
}