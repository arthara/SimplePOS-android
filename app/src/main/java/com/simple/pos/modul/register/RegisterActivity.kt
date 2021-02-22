package com.simple.pos.modul.register

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.simple.pos.R
import com.simple.pos.databinding.ActivityRegisterBinding
import com.simple.pos.modul.register.submodel.RegisteringUser
import com.simple.pos.shared.model.User
import com.simple.pos.shared.extension.TAG
import top.defaults.view.TextButton
import top.defaults.view.TextButtonEffect


class RegisterActivity: AppCompatActivity(), RegisterContract.View {
    private val presenter = RegisterPresenter(this)
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initializeButtonOnclick()
        initializeButton()
        onClickMasuk()
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

    private fun initializeButton(){
        binding.btnMasuk.setEffects(object : TextButtonEffect {
            private var textButton: TextButton? = null
            override fun init(textButton: TextButton) {
                this.textButton = textButton
            }

            override fun actionDown() {
                textButton!!.alpha = 0.5f
            }

            override fun actionUp() {
                textButton!!.alpha = 1f
            }
        })
    }

    private fun onClickMasuk(){
        binding.btnMasuk.setOnClickListener(View.OnClickListener {
            redirectToLogin()
        })
    }
}