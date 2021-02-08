package com.simple.pos.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.simple.pos.R
import com.simple.pos.dashboard.DashboardActivity
import com.simple.pos.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity(), LoginContract.View {
    private val presenter = LoginPresenter(this)
    private lateinit var binding: ActivityLoginBinding

    companion object{
        private val TAG = LoginActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        findViewById<Button>(R.id.login).setOnClickListener(View.OnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            presenter.authenticate(email, password)
        })
    }

    override fun redirectToHome() {
        intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        Log.d(TAG, "Login Successfully, Redirect to Home")
        finish()
    }
}