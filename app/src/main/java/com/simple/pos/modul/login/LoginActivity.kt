package com.simple.pos.modul.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.simple.pos.R
import com.simple.pos.base.util.UtilProvider
import com.simple.pos.databinding.ActivityLoginBinding
import com.simple.pos.modul.dashboard.DashboardActivity
import com.simple.pos.modul.register.RegisterActivity
import com.simple.pos.shared.extension.TAG
import com.simple.pos.shared.extension.showToast
import com.simple.pos.shared.util.TokenUtil
import top.defaults.view.TextButton
import top.defaults.view.TextButtonEffect

class LoginActivity: AppCompatActivity(), LoginContract.View {
    private val presenter = LoginPresenter(this)
    private lateinit var binding: ActivityLoginBinding

    companion object{
        private const val REGISTER_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.automaticallyLogin()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initializeButtons()
        initializeButton()
    }

    private fun initializeButtons(){
        findViewById<Button>(R.id.login).setOnClickListener(View.OnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()

            presenter.authenticate(email, password)
        })

        binding.btnDaftar.setOnClickListener(View.OnClickListener {
            redirectToRegister()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REGISTER_REQUEST_CODE->{
                //if register successful get user then redirect to home
                if(resultCode == RESULT_OK){
                    val tokenUtil = UtilProvider.getUtil(TokenUtil::class.java) as TokenUtil

                    presenter.requestUser(tokenUtil.sessionData)
                }
            }
        }
    }

    override fun redirectToHome() {
        intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        Log.d(TAG, "Login Successfully, Redirect to Home")
        finish()
    }

    override fun redirectToRegister() {
        intent = Intent(this, RegisterActivity::class.java)
        startActivityForResult(intent, REGISTER_REQUEST_CODE)
        Log.d(TAG, "Redirect to Register")
    }

    override fun showError(message: String?) {
        showToast("Login Fail")
    }

    private fun initializeButton(){
        binding.btnDaftar.setEffects(object : TextButtonEffect {
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
}