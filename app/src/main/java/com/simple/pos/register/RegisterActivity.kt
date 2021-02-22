package com.simple.pos.register

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.simple.pos.R
import com.simple.pos.databinding.ActivityRegisterBinding
import com.simple.pos.register.submodel.RegisteringUser
import com.simple.pos.shared.model.User
import com.simple.pos.shared.extension.TAG

class RegisterActivity: AppCompatActivity(), RegisterContract.View {
    private val presenter = RegisterPresenter(this)
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initializeButtonOnclick()
    }

    private fun initializeButtonOnclick(){
        findViewById<Button>(R.id.register_btn).setOnClickListener(View.OnClickListener {
            val username = binding.registerUsernameEt.text.toString()
            val email = binding.registerEmailEt.text.toString()
            val password = binding.registerPasswordEt.text.toString()
            val passwordConfirmation = binding.registerConfirmPasswordEt.text.toString()
            var registeringUser = RegisteringUser(
                    username, passwordConfirmation, User(email, password)
            )

            presenter.register(registeringUser)
        })

    }

    override fun redirectToLogin() {
        finish()
    }

    override fun redirectToHome() {
        setResult(Activity.RESULT_OK)
        Log.d(TAG, "Registered Successfully, Redirect to Home")
        finish()
    }
}