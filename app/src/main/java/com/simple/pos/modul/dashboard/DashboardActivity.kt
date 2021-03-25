package com.simple.pos.modul.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.simple.pos.R
import com.simple.pos.modul.dashboard.fragment.belanja.BelanjaFragment
import com.simple.pos.modul.dashboard.fragment.inventory.InventoryFragment
import com.simple.pos.modul.dashboard.fragment.main.MainFragment
import com.simple.pos.modul.profiluser.ProfileUserActivity
import com.simple.pos.modul.storeinput.StoreInputActivity

class DashboardActivity: AppCompatActivity(), DashboardContract.View{
    private var selectedFragment: Fragment? = null
    private var mainFragment: MainFragment? = null
    private var inventoryFragment: InventoryFragment? = null
    private var belanjaFragment: BelanjaFragment? = null
    private val presenter = DashboardPresenter(this)

    companion object {
        private const val LOGOUT_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        initializeBottomNavBarClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            LOGOUT_REQUEST_CODE->{
                // close this activity when user log out
                if(resultCode == RESULT_OK)
                    finish()
            }
        }
    }

    private fun initializeBottomNavBarClick() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavBar)

        bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mainMenuItem->changePageToMain()
                R.id.inventoryMenuItem->changePageToInventory()
                R.id.shopMenuItem->changePageToShopping()
                R.id.accountMenuItem->redirectToAccount()
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    private fun showChangedPage() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.dashboardFragment, selectedFragment!!)
                .commit()
    }

    override fun changePageToMain() {
        if(mainFragment == null)
            mainFragment = MainFragment()

        selectedFragment = mainFragment
        showChangedPage()
    }

    override fun changePageToShopping() {
        if(belanjaFragment == null)
            belanjaFragment = BelanjaFragment()

        selectedFragment = belanjaFragment
        showChangedPage()
    }

    override fun changePageToInventory() {
        if(inventoryFragment == null)
            inventoryFragment = InventoryFragment()

        selectedFragment = inventoryFragment
        showChangedPage()
    }

    override fun redirectToAccount() {
        val intent = Intent(this, ProfileUserActivity::class.java)

        startActivityForResult(intent, LOGOUT_REQUEST_CODE)
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