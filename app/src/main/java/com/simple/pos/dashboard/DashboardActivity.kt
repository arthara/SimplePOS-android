package com.simple.pos.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.simple.pos.storeinput.StoreInputActivity

class DashboardActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, StoreInputActivity::class.java)
        startActivity(intent)
        finish()
    }
}