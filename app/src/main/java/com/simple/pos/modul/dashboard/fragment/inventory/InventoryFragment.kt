package com.simple.pos.modul.dashboard.fragment.inventory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.simple.pos.R
import com.simple.pos.modul.dashboard.fragment.inventory.subfragment.category.CategoryFragment
import com.simple.pos.shared.extension.TAG

class InventoryFragment: Fragment(), TabLayout.OnTabSelectedListener {
    private var selectedFragment: Fragment? = null
    private var categoryFragment: CategoryFragment? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard_inventory, container, false)

        view.findViewById<TabLayout>(R.id.inventoryTabLayout).addOnTabSelectedListener(this)
        return view
    }
    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        Log.i(TAG, "Tab selected: ${tab?.position}")
        when(tab?.position){
            0->changePageToStock()
            1->changePageToCategory()
        }
    }

    private fun changePageToCategory() {
        if(categoryFragment == null)
            categoryFragment = CategoryFragment()

        selectedFragment = categoryFragment
        Log.i(TAG, "Changing list to category")
        changeCurrentPage()
    }

    private fun changeCurrentPage(){
        childFragmentManager.beginTransaction()
                .replace(R.id.inventorySubFragment, selectedFragment!!)
                .commit()
    }

    private fun changePageToStock() {
        changeCurrentPage()
    }
}