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
import com.simple.pos.modul.profiluser.profilnote.NoteStoreActivity
import com.simple.pos.modul.profiluser.storesetting.ProfileStoreActivity

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

/*
        findViewById<TextView>(R.id.tvPengaturanToko).setOnClickListener{
            redirectToStoreConfiguration()
        }

        findViewById<TextView>(R.id.tvPengaturanNote).setOnClickListener{
            redirectToStructConfiguration()
        }


*/

    }

    override fun showUserAndStoreName(username: String, storeName: String) {
        findViewById<TextView>(R.id.tvProfileName).text = username
        findViewById<TextView>(R.id.tvStoreNameProfile).text = storeName
    }

    override fun redirectToStoreConfiguration() {
        val intent = Intent(this, ProfileStoreActivity::class.java)
        startActivity(intent)
    }

    override fun redirectToStructConfiguration() {
        val intent = Intent(this, NoteStoreActivity::class.java)
        startActivity(intent)
    }

    override fun redirectToPaymentConfiguration() {
        // TODO("Not yet implemented")
    }

    override fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)

        setResult(RESULT_OK)
        startActivity(intent)
        finish()
    }

    override fun redirectToDashboard() {
        finish()
    }
}