package com.simple.pos.modul.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simple.pos.R
import com.simple.pos.modul.dashboard.fragment.main.MainFragment
import com.simple.pos.modul.storeinput.StoreInputActivity

class DashboardActivity: AppCompatActivity(), DashboardContract.View{
    private var mainFragment: MainFragment? = null
    private var selectedFragment: Fragment? = null
    private val presenter = DashboardPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        presenter.askStore()
        initializeBottomNavBarClick()
    }

    private fun initializeBottomNavBarClick() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavBar)

        //TODO: Add case for other page
        bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mainMenuItem->{
                    changePageToMain()
                }
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    private fun showChangedPage() {
        supportFragmentManager.beginTransaction().replace(R.id.dashboardFragment, selectedFragment!!)
    }

    override fun changePageToMain() {
        if(mainFragment != null)
            selectedFragment = mainFragment
        else{
            mainFragment = MainFragment()
            selectedFragment = mainFragment
        }
        showChangedPage()
    }

    override fun changePageToShopping() {
        TODO("Not yet implemented")
    }

    override fun changePageToInventory() {
        TODO("Not yet implemented")
    }

    override fun changePageToAccount() {
        TODO("Not yet implemented")
    }

    override fun redirectToCheckout() {
        TODO("Not yet implemented")
    }

    override fun redirectToStoreInput() {
        val intent = Intent(this, StoreInputActivity::class.java)
        startActivity(intent)
        finish()
    }
}