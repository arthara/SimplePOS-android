package com.simple.pos.modul.profiluser

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.R
import com.simple.pos.modul.login.LoginActivity

class ProfileUserActivity: AppCompatActivity(), ProfileUserContract.View {
    private val presenter  = ProfileUserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initializeOnClicks()
        presenter.showUsernameAndStorename()
    }

    private fun initializeOnClicks() {
        findViewById<ImageView>(R.id.ivBackArrow).setOnClickListener{
            redirectToDashboard()
        }
        findViewById<RelativeLayout>(R.id.storeConfigurationBtn).setOnClickListener{
            redirectToStoreConfiguration()
        }
        findViewById<RelativeLayout>(R.id.struckConfigurationBtn).setOnClickListener{
            redirectToStructConfiguration()
        }
        findViewById<RelativeLayout>(R.id.paymentConfigurationBtn).setOnClickListener{
            redirectToStructConfiguration()
        }
        findViewById<Button>(R.id.logoutBtn).setOnClickListener{
            presenter.logout()
        }
    }

    override fun showUserAndStoreName(username: String, storeName: String) {
        findViewById<TextView>(R.id.tvProfileName).text = username
        findViewById<TextView>(R.id.tvStoreNameProfile).text = storeName
    }

    override fun redirectToStoreConfiguration() {
        // TODO("Not yet implemented")
    }

    override fun redirectToStructConfiguration() {
        // TODO("Not yet implemented")
    }

    override fun redirectToPaymentConfiguration() {
        // TODO("Not yet implemented")
    }

    override fun redirectToLogin() {
        startActivity(Intent(this,
                LoginActivity::class.java))
        finish()
    }

    override fun redirectToDashboard() {
        finish()
    }
}